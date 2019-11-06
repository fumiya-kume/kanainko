package kuu.nagoya.dashboard.usecase

import kuu.nagoya.entity.recordentity.Record

internal interface RecordListRepository {
    suspend fun storeRecordList(recordList: List<Record>)
}