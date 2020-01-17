package kuu.nagoya.feature.result.usecase

import kuu.nagoya.data.tmprecorddata.entity.TmpRecordDataEntity

interface RecordDataToModelVoiceDataUsecase {
    suspend fun execute(recordData: TmpRecordDataEntity): List<Short>
}