package kuu.nagoya.dashboard.usecase

internal interface RecordListRepository {
    suspend fun storeRecordList(recordList: List<kuu.nagoya.entity.recordentity.Record>)
}