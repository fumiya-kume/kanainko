package kuu.nagoya.feature.result.usecase

internal sealed class PredictAudioStatus {
    object NeedMore : PredictAudioStatus()
    class Done(val result: String) : PredictAudioStatus()
}
