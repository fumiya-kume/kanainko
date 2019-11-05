package kuu.nagoya.dashboard.usecase

internal interface RecordListRepository {
    suspend fun storeRecordList(recordList: List<Record>)
}