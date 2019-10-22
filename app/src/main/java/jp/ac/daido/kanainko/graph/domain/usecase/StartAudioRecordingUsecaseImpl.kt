package jp.ac.daido.kanainko.graph.domain.usecase

import jp.ac.daido.kanainko.record.domain.repository.AudioRepository
import jp.ac.daido.kanainko.record.domain.usecase.StartAudioRecordingUsecase

internal class StartAudioRecordingUsecaseImpl(
    private val audioRepository: AudioRepository
) : StartAudioRecordingUsecase {
    override suspend fun execute() {
        audioRepository.startAudioRecord()
    }
}
