package kuu.nagoya.feature.record.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

internal class RecordViewModel(
    tmpRecordLiveDataFactory: TmpRecordLiveDataFactory
) : ViewModel() {
    val tmpRecordLiveData = tmpRecordLiveDataFactory.create()

    fun createTmpRecording() {
        viewModelScope.launch {
            tmpRecordLiveData.create()
        }
    }
}
