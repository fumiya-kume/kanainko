package kuu.nagoya.dashboard.usecase

import kuu.nagoya.entity.recordentity.Record

internal interface RecordListReadonlyRepository {
    suspend fun loadRecordList(): List<Record>

}