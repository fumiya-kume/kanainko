package kuu.nagoya.feature.record.view

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kuu.nagoya.feature.record.entity.RecordFilePath
import kuu.nagoya.feature.record.usecase.LoadRecordFilePathUsecase


internal class RecordFilePathLiveData(
    coroutineScope: CoroutineScope,
    private val createRecordFilePathUsecase: LoadRecordFilePathUsecase
) : LiveData<RecordFilePath>() {
    init {
        coroutineScope.launch {
            postValue(
                createRecordFilePathUsecase.execute()
            )
        }
    }
}