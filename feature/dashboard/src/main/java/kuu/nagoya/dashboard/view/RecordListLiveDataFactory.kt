package kuu.nagoya.dashboard.view

import kotlinx.coroutines.CoroutineScope
import kuu.nagoya.dashboard.usecase.RecordListReadonlyRepository
import kuu.nagoya.dashboard.usecase.RemoveAllRecordListUsecase
import kuu.nagoya.dashboard.usecase.ResetRecordListUsecase

internal class RecordListLiveDataFactory(
    private val recordListReadonlyRepository: RecordListReadonlyRepository,
    private val resetRecordListUsecase: ResetRecordListUsecase,
    private val removeAllRecordListUsecase: RemoveAllRecordListUsecase
) {
    fun create(
        coroutineScope: CoroutineScope
    ): RecordListLiveData {
        return RecordListLiveData(
            coroutineScope,
            recordListReadonlyRepository,
            resetRecordListUsecase,
            removeAllRecordListUsecase
        )
    }
}
