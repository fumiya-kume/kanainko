package kuu.nagoya.feature.result.usecase.impl

import com.prevent.voice_data.domain.VoiceDataRepository
import java.io.File
import kuu.nagoya.data.tmprecorddata.entity.TmpRecordDataEntity
import kuu.nagoya.feature.result.usecase.RecordDataToModelVoiceFileUsecase

class RecordDataToModelVoiceFileUsecaseImpl(
    private val voiceDataRepository: VoiceDataRepository
) : RecordDataToModelVoiceFileUsecase {
    override suspend fun execute(tmpRecordDataEntity: TmpRecordDataEntity): File {
        return voiceDataRepository.loadModelVoiceByName(
            "P${tmpRecordDataEntity.id.toString().padStart(
                3,
                '0'
            )}.wav"
        )
    }
}