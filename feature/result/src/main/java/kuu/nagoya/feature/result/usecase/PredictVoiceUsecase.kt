package kuu.nagoya.feature.result.usecase

import kuu.nagoya.data.tmprecorddata.entity.TmpRecordDataEntity

interface PredictVoiceUsecase {
    suspend fun execute(tmpRecordDataEntity: TmpRecordDataEntity): String
}