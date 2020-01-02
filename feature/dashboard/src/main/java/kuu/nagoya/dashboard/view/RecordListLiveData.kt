package kuu.nagoya.dashboard.view

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kuu.nagoya.dashboard.usecase.RecordListReadonlyRepository
import kuu.nagoya.dashboard.usecase.RemoveAllRecordListUsecase
import kuu.nagoya.dashboard.usecase.ResetRecordListUsecase
import kuu.nagoya.dashboard.viewentity.RecordViewEntity
import kuu.nagoya.dashboard.viewentity.convert

internal class RecordListLiveData(
    private val coroutineScope: CoroutineScope,
    private val recordListReadonlyRepository: RecordListReadonlyRepository,
    private val recordListUsecase: ResetRecordListUsecase,
    private val removeAllRecordListUsecase: RemoveAllRecordListUsecase
) : LiveData<List<RecordViewEntity>>() {
    init {
        refreshRecordList()
    }

    private fun refreshRecordList() {
        coroutineScope.launch {
            postValue(
                recordListReadonlyRepository
                    .loadRecordList()
                    .convert()
            )
        }
    }

    fun reset() {
        coroutineScope.launch {
            recordListUsecase
                .execite()
            refreshRecordList()
        }
    }

    fun removeAllRecord() {
        coroutineScope.launch {
            removeAllRecordListUsecase
                .execute()
            refreshRecordList()
        }
    }
}
