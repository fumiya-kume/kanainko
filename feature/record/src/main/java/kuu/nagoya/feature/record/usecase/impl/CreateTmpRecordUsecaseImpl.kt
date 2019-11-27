package kuu.nagoya.feature.record.usecase.impl

import kuu.nagoya.feature.record.entity.TmpRecord
import kuu.nagoya.feature.record.entity.toRecordName
import kuu.nagoya.feature.record.usecase.CreateTmpRecordUsecase


internal class CreateTmpRecordUsecaseImpl : CreateTmpRecordUsecase {
    override suspend fun execute(): TmpRecord {
//        fun generateRecordTitle(): String {
//            val now = Date(System.currentTimeMillis())
//            val nowString = SimpleDateFormat("yyyyMMddHHmmssSS").format(now)
//            return nowString
//        }
//
//        val existRecordList = recordListReadonlyRepository.loadRecordList()
//
//        val nextId = existRecordList.maxBy { it.id }?.id?.plus(1) ?: 0
//
//        val newTitle = generateRecordTitle()
//
//        return TmpRecord(
//            nextId,
//            newTitle.toRecordName()
//        )

        return TmpRecord(
            0,
            "aa".toRecordName()
        )
    }
}