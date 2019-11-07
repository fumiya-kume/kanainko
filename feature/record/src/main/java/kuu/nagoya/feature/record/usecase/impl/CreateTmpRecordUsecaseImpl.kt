package kuu.nagoya.feature.record.usecase.impl

import kuu.nagoya.feature.record.entity.TmpRecord
import kuu.nagoya.feature.record.entity.toRecordName
import kuu.nagoya.feature.record.usecase.CreateTmpRecordUsecase
import java.text.SimpleDateFormat
import java.util.Date

internal class CreateTmpRecordUsecaseImpl :
    CreateTmpRecordUsecase {
    override suspend fun execute(): TmpRecord {
        val now = Date(System.currentTimeMillis())
        val nowString = SimpleDateFormat("yyyyMMddHHmmssSS").format(now)
        return TmpRecord(
            0,
            nowString.toString().toRecordName()
        )
    }
}