package kuu.nagoya.feature.result.usecase.impl

import android.content.Context
import java.io.BufferedReader
import java.io.InputStreamReader
import kuu.nagoya.feature.result.usecase.PredictAudioStatus
import kuu.nagoya.feature.result.usecase.PredictAudioUsecase
import org.tensorflow.contrib.android.TensorFlowInferenceInterface

internal class PredictAudioUsecaseImpl(
    context: Context
) : PredictAudioUsecase {

    companion object {
        private const val sampleRate = 16000
        private const val sample_duration_ms = 1000

        val recordingLength = sampleRate * sample_duration_ms / 1000
    }

    private val labelFilePath = "labels.txt"
    private val modelFilePath = "file:///android_asset/voice_command.pb"

    private val sampleRateName = "decoded_sample_data:1"
    private val inputDataName = "decoded_sample_data:0"
    private val outputScoreName = "labels_softmax"

    private val labels: List<String> =
        BufferedReader(
            InputStreamReader(
                context.assets.open(labelFilePath)
            )
        ).readLines()

    private val tensorFlowInferenceInterface = TensorFlowInferenceInterface(
        context.assets,
        modelFilePath
    )

    override fun execute(data: List<Short>): PredictAudioStatus {

        if (data.size < recordingLength) {
            // need data amount of recordingLength
            return PredictAudioStatus.NeedMore
        }

        tensorFlowInferenceInterface.feed(sampleRateName, intArrayOf(sampleRate))
        tensorFlowInferenceInterface.feed(
            inputDataName,
            data.map { it / 32767.0f }.take(recordingLength).toFloatArray(),
            recordingLength.toLong(),
            1L
        )

        val outputScoreNames = arrayOf(outputScoreName)
        tensorFlowInferenceInterface.run(outputScoreNames)

        val outputScores = FloatArray(labels.size)
        tensorFlowInferenceInterface.fetch(outputScoreName, outputScores)

        return PredictAudioStatus.Done(
            labels.elementAt(
                outputScores.indexOf(
                    outputScores.max() ?: 0F
                )
            )
        )
    }
}
