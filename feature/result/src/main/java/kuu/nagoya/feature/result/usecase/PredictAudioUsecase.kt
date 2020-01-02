package kuu.nagoya.feature.result.usecase

internal interface PredictAudioUsecase {
    fun execute(data: List<Short>): PredictAudioStatus

    companion object {
        val recordingLength: Int = 16000
    }
}
