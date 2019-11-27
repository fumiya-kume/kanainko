package kuu.nagoya.feature.record.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

internal class RecordViewModel(
    tmpRecordLiveDataFactory: TmpRecordLiveDataFactory
) : ViewModel() {
    val tmpRecordLiveData = tmpRecordLiveDataFactory.create(viewModelScope)
}
