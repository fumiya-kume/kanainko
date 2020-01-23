package kuu.nagoya.feature.result.usecase.impl

import kuu.nagoya.data.tmprecorddata.TmpRecordDataReadonlyRepository
import kuu.nagoya.feature.result.service.PlayAudioService
import kuu.nagoya.feature.result.usecase.PlayModelVoiceUsecase
import kuu.nagoya.feature.result.usecase.RecordDataToModelVoiceFileUsecase

class PlayModelVoiceUsecaseImpl(
    private val playAudioService: PlayAudioService,
    private val recordDataToModelVoiceFileUsecase: RecordDataToModelVoiceFileUsecase,
    private val tmpRecordDataReadonlyRepository: TmpRecordDataReadonlyRepository
) : PlayModelVoiceUsecase {
    override suspend fun execute() {
        val recordData = tmpRecordDataReadonlyRepository.loadTmpRecordData()
        val voice = recordDataToModelVoiceFileUsecase.execute(recordData)
        if (voice.exists()) {
            playAudioService.playAudio(voice.absolutePath)
        }
    }
}