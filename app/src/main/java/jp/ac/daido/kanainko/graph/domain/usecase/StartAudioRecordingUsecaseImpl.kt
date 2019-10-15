package jp.ac.daido.kanainko.graph.domain.usecase

import jp.ac.daido.kanainko.graph.domain.repository.AudioRepository

internal class StartAudioRecordingUsecaseImpl(
    private val audioRepository: AudioRepository
) : StartAudioRecordingUsecase {
    override suspend fun execute() {
        audioRepository.startAudioRecord()
    }
}