package kuu.nagoya.dashboard.infra

import kuu.nagoya.dashboard.entity.Record
import kuu.nagoya.dashboard.entity.convert
import kuu.nagoya.dashboard.entity.convertBack
import kuu.nagoya.dashboard.usecase.RecordListReadonlyRepository
import kuu.nagoya.dashboard.usecase.RecordListRepository
import kuu.nagoya.data.recorddata.RecordDataRepository

internal class RecordListRepositoryImpl(
    private val recordDataRepository: RecordDataRepository
) : RecordListReadonlyRepository, RecordListRepository {

    override suspend fun loadRecordList():
            List<Record> {
        return recordDataRepository.loadRecordDataList().convert()
    }

    override suspend fun storeRecordList(recordList: List<Record>) {
        recordDataRepository
            .storeRecordDataList(
                recordList.convertBack()
            )
    }
}
