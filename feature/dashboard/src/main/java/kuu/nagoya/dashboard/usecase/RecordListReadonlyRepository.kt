package kuu.nagoya.dashboard.usecase

import kuu.nagoya.dashboard.entity.Record

internal interface RecordListReadonlyRepository {
    suspend fun loadRecordList(): List<Record>
}