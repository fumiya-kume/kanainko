package kuu.nagoya.feature.result.usecase.impl

import kuu.nagoya.data.tmprecorddata.entity.TmpRecordDataEntity
import kuu.nagoya.feature.result.usecase.PredictVoiceUsecase

class PredictVoiceUsecaseImpl :
    PredictVoiceUsecase {
    override suspend fun execute(tmpRecordDataEntity: TmpRecordDataEntity): String {
        return tmpRecordDataEntity.chooseCharacter.toString()
    }
}