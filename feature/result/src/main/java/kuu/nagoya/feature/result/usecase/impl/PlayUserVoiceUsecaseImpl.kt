package kuu.nagoya.feature.result.usecase.impl

import kuu.nagoya.data.tmprecorddata.TmpRecordDataReadonlyRepository
import kuu.nagoya.feature.result.service.PlayAudioService
import kuu.nagoya.feature.result.usecase.PlayUserVoiceUsecase

class PlayUserVoiceUsecaseImpl(
    private val tmpRecordDataReadonlyRepository: TmpRecordDataReadonlyRepository,
    private val playAudioService: PlayAudioService
) : PlayUserVoiceUsecase {
    override suspend fun exeute() {
        val audioPath = tmpRecordDataReadonlyRepository.loadTmpRecordData().audioFilePath
        if (audioPath != null) {
            playAudioService.playAudio(audioPath)
        }
    }
}
