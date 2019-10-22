package jp.ac.daido.kanainko.record.domain.usecase

internal interface StartAudioRecordingUsecase {
    suspend fun execute()
}
