package kuu.nagoya.data.recorddata

import kuu.nagoya.data.recorddata.model.RecordDataModel

interface RecordDataRepository {
    suspend fun storeRecordDataList(dataList: List<RecordDataModel>)
    suspend fun loadRecordDataList(): List<RecordDataModel>
}
