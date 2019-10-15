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

    init {

    }

    fun startRecording() {
        viewModelScope.launch {
            startAudioRecordingUsecase.execute()
        }
        _soundVolumeLiveData = soundVolumeLiveDataFactory.create(viewModelScope)
        _soundRawFileLiveData = soundRawDataLiveDataFactory.create(viewModelScope)
    }


    private lateinit var _soundVolumeLiveData: SoundVolumeLiveData
    val soundVolumeLiveData: LiveData<SoundVolumeViewEntity>
        get() = _soundVolumeLiveData

    private lateinit var _soundRawFileLiveData: SoundRawDataLiveData
    val soundRawLiveData: LiveData<SoundRawDataViewEntity>
        get() = _soundRawFileLiveData
}