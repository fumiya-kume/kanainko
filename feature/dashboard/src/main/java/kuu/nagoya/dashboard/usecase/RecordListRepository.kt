package kuu.nagoya.dashboard.usecase

import kuu.nagoya.dashboard.entity.Record

internal interface RecordListRepository {
    suspend fun storeRecordList(recordList: List<Record>)
}
