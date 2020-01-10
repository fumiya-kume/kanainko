package kuu.nagoya.feature.result.usecase

import java.io.File
import kuu.nagoya.data.tmprecorddata.entity.TmpRecordDataEntity

interface RecordDataToModelVoiceFileUsecase {
    suspend fun execute(tmpRecordDataEntity: TmpRecordDataEntity): File
}