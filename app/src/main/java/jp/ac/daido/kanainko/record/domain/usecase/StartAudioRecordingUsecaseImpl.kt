package jp.ac.daido.kanainko.record.domain.usecase

import jp.ac.daido.kanainko.record.domain.repository.AudioRepository

internal class StartAudioRecordingUsecaseImpl(
    private val audioRepository: AudioRepository
) : StartAudioRecordingUsecase {
    override suspend fun execute() {
        audioRepository.startAudioRecord()
    }
}
