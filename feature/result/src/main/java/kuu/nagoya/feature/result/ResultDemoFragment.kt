package kuu.nagoya.feature.result

import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kuu.nagoya.feature.result.databinding.FragmentDemoResultBinding
import org.tensorflow.contrib.android.TensorFlowInferenceInterface
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList
import java.util.Queue

internal class ResultDemoFragment : Fragment() {

    private val sampleRate = 16000
    private val sample_duration_ms = 1000
    private val recordingLength = sampleRate * sample_duration_ms / 1000


    private val audioBufferSize = AudioRecord.getMinBufferSize(
        sampleRate,
        AudioFormat.CHANNEL_IN_MONO,
        AudioFormat.ENCODING_PCM_16BIT
    )

    private val audioRecord: AudioRecord = AudioRecord(
        MediaRecorder.AudioSource.DEFAULT,
        sampleRate,
        AudioFormat.CHANNEL_IN_MONO,
        AudioFormat.ENCODING_PCM_16BIT,
        audioBufferSize
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDemoResultBinding
            .inflate(
                layoutInflater,
                container,
                false
            )

        val labelFilePath = "labels.txt"
        val modelFilePath = "file:///android_asset/voice_command.pb"

        val sampleRateName = "decoded_sample_data:1"
        val inputDataName = "decoded_sample_data:0"
        val outputScoreName = "labels_softmax"

        val labels: List<String> =
            BufferedReader(
                InputStreamReader(
                    requireActivity().assets.open(labelFilePath)
                )
            ).readLines()

        val tensorFlowInferenceInterface = TensorFlowInferenceInterface(
            requireActivity().assets,
            modelFilePath
        )

        audioRecord.positionNotificationPeriod = audioBufferSize / 2

        val predictionSource: Queue<Float> = LinkedList()

        audioRecord.setRecordPositionUpdateListener(object :
            AudioRecord.OnRecordPositionUpdateListener {
            override fun onMarkerReached(recorder: AudioRecord?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onPeriodicNotification(recorder: AudioRecord?) {
                if (recorder == null) {
                    return
                }
                val audioArray = ShortArray(audioBufferSize / 2)
                recorder.read(audioArray, 0, audioArray.size)

                predictionSource.addAll(
                    audioArray
                        .map { it / 32767.0f }
                )

                if (predictionSource.size > recordingLength) {
                    tensorFlowInferenceInterface.feed(sampleRateName, intArrayOf(sampleRate))
                    tensorFlowInferenceInterface.feed(
                        inputDataName,
                        predictionSource.take(recordingLength).toFloatArray(),
                        recordingLength.toLong(),
                        1L
                    )

                    val outputScoreNames = arrayOf(outputScoreName)
                    tensorFlowInferenceInterface.run(outputScoreNames)

                    val outputScores = FloatArray(labels.size)
                    tensorFlowInferenceInterface.fetch(outputScoreName, outputScores)
                    Log.d(
                        "result",
                        labels.elementAt(outputScores.indexOf(outputScores.max() ?: 0F))
                    )

                    binding.fragmentDemoResultAnswerTextView.text =
                        labels.elementAt(outputScores.indexOf(outputScores.max() ?: 0F))
                }
            }
        })

        audioRecord.startRecording()

        return binding.root
    }
}