package kuu.nagoya.feature.result.usecase

import kuu.nagoya.data.tmprecorddata.entity.TmpRecordDataEntity

interface RecordDataToUserVoiceDataUsecase {
    fun execute(tmpRecordDataEntity: TmpRecordDataEntity): List<Short>
}