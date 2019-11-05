package kuu.nagoya.dashboard.usecase

internal interface RecordListReadonlyRepository {
    suspend fun loadRecordList(): List<kuu.nagoya.entity.recordentity.Record>

}