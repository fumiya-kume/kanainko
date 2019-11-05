package kuu.nagoya.dashboard.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

internal class DashboardViewModel(
    private val recordListLiveDataFactory: RecordListLiveDataFactory
) : ViewModel() {
    val recordListLiveData = recordListLiveDataFactory.create(viewModelScope)

    fun reset() {
        recordListLiveData.reset()
    }

    fun removeAll() {
        recordListLiveData.removeAllRecord()
    }
}