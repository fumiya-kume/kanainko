package kuu.nagoya.feature.result

import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import java.io.FileInputStream
import java.util.LinkedList
import java.util.Queue
import kuu.nagoya.feature.result.databinding.FragmentDemoResultBinding
import kuu.nagoya.feature.result.usecase.PredictAudioStatus
import kuu.nagoya.feature.result.usecase.PredictAudioUsecase
import org.koin.android.ext.android.inject

internal class ResultDemoFragment : Fragment() {

    private val predictAudioUsecase: PredictAudioUsecase by inject()

    private val sampleRate = 16000
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

        audioRecord.positionNotificationPeriod = audioBufferSize / 2

        val predictionSource: Queue<Short> = LinkedList()

        audioRecord.setRecordPositionUpdateListener(object :
            AudioRecord.OnRecordPositionUpdateListener {
            override fun onMarkerReached(recorder: AudioRecord?) {
                TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
            }

            override fun onPeriodicNotification(recorder: AudioRecord?) {
                if (recorder == null) {
                    return
                }
                val audioArray = ShortArray(audioBufferSize / 2)
                recorder.read(audioArray, 0, audioArray.size)

                predictionSource.addAll(
                    audioArray
                        .toList()
                )

                if (predictionSource.size > PredictAudioUsecase.recordingLength) {
                    val result =
                        predictAudioUsecase.execute(predictionSource.take(PredictAudioUsecase.recordingLength))
                    when (result) {
                        is PredictAudioStatus.NeedMore -> return
                        is PredictAudioStatus.Done -> {
                            val resultString = result.result
                            binding.fragmentDemoResultAnswerTextView.text = resultString
                        }
                    }
                }
            }
        })

        audioRecord.startRecording()

        binding
            .fragmentDemoResultPickFileButton
            .setOnClickListener {
                val mediaFilePath = requireContext().externalMediaDirs.first().absolutePath
                val localDemoWaveFilepath = mediaFilePath + "dog.wav"

                val fileStream = FileInputStream(localDemoWaveFilepath)
            }

        return binding.root
    }
}
