package kuu.nagoya.feature.record.view

import kotlinx.coroutines.CoroutineScope
import kuu.nagoya.feature.record.usecase.LoadRecordFilePathUsecase

internal class TmpRecordLiveDataFactory(
    private val createRecordFilePathUsecase: LoadRecordFilePathUsecase
) {
    fun create(coroutineScope: CoroutineScope): RecordFilePathLiveData {
        return RecordFilePathLiveData(
            coroutineScope,
            createRecordFilePathUsecase
        )
    }
}
