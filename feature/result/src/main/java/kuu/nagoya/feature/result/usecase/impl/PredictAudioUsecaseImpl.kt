package kuu.nagoya.feature.result.usecase.impl

import android.content.Context
import kuu.nagoya.feature.result.usecase.PredictAudioStatus
import kuu.nagoya.feature.result.usecase.PredictAudioUsecase
import org.tensorflow.contrib.android.TensorFlowInferenceInterface
import java.io.BufferedReader
import java.io.InputStreamReader

internal class PredictAudioUsecaseImpl(
    context: Context
) : PredictAudioUsecase {

    companion object {
        private val sampleRate = 16000
        private val sample_duration_ms = 1000

        val recordingLength = sampleRate * sample_duration_ms / 1000
    }

    val labelFilePath = "labels.txt"
    val modelFilePath = "file:///android_asset/voice_command.pb"

    val sampleRateName = "decoded_sample_data:1"
    val inputDataName = "decoded_sample_data:0"
    val outputScoreName = "labels_softmax"

    val labels: List<String> =
        BufferedReader(
            InputStreamReader(
                context.assets.open(labelFilePath)
            )
        ).readLines()

    val tensorFlowInferenceInterface = TensorFlowInferenceInterface(
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