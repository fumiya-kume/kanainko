package kuu.nagoya.dashboard.usecase.impl

import kuu.nagoya.dashboard.usecase.RecordListRepository
import kuu.nagoya.dashboard.usecase.ResetRecordListUsecase

internal class ResetRecordListUsecaseImpl(
    private val recordListRepository: RecordListRepository
) : ResetRecordListUsecase {
    override suspend fun execute() {
        recordListRepository.storeRecordList(
            emptyList()
        )
    }
}
