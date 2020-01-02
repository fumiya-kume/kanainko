package kuu.nagoya.dashboard.usecase.impl

import kuu.nagoya.dashboard.usecase.RecordListRepository
import kuu.nagoya.dashboard.usecase.RemoveAllRecordListUsecase

internal class RemoveAllRecordListUsecaseImpl(
    private val recordListRepository: RecordListRepository
) : RemoveAllRecordListUsecase {
    override suspend fun execute() {
        recordListRepository.storeRecordList(emptyList())
    }
}
