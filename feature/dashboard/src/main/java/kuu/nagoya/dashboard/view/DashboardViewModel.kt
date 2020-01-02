package kuu.nagoya.dashboard.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kuu.nagoya.dashboard.usecase.PlayAudioUsecase
import kuu.nagoya.dashboard.viewentity.RecordViewEntity
import kuu.nagoya.dashboard.viewentity.convertBack

internal class DashboardViewModel(
    private val recordListLiveDataFactory: RecordListLiveDataFactory,
    private val playAudioUsecase: PlayAudioUsecase
) : ViewModel() {
    val recordListLiveData = recordListLiveDataFactory.create(viewModelScope)

    fun reset() {
        recordListLiveData.reset()
    }

    fun removeAll() {
        recordListLiveData.removeAllRecord()
    }

    fun playRecordAudio(recordViewEntity: RecordViewEntity) {
        viewModelScope.launch {
            playAudioUsecase.execute(recordViewEntity.convertBack())
        }
    }
}
