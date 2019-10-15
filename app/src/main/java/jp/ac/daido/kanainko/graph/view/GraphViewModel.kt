package jp.ac.daido.kanainko.graph.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.ac.daido.kanainko.graph.domain.usecase.StartAudioRecordingUsecase
import jp.ac.daido.kanainko.graph.view.viewentity.SoundRawDataViewEntity
import jp.ac.daido.kanainko.graph.view.viewentity.SoundVolumeViewEntity
import kotlinx.coroutines.launch

internal class GraphViewModel(
    private val soundVolumeLiveDataFactory: SoundVolumeLiveDataFactory,
    private val soundRawDataLiveDataFactory: SoundRawDataLiveDataFactory,
    private val startAudioRecordingUsecase: StartAudioRecordingUsecase
) : ViewModel() {

    fun startRecording() {
        viewModelScope.launch {
            startAudioRecordingUsecase.execute()
        }
    }

    var soundVolumeLiveData: LiveData<SoundVolumeViewEntity> =
        soundVolumeLiveDataFactory.create(viewModelScope)

    val soundRawLiveData: LiveData<SoundRawDataViewEntity> =
        soundRawDataLiveDataFactory.create(viewModelScope)
}