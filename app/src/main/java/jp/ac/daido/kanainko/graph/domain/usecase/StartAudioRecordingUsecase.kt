package jp.ac.daido.kanainko.graph.domain.usecase

internal interface StartAudioRecordingUsecase {
    suspend fun execute()
}