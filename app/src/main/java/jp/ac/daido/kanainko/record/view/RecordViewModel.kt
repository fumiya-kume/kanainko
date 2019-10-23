package jp.ac.daido.kanainko.record.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.ac.daido.kanainko.record.view.viewentity.RawSoundDataViewEntity

internal class RecordViewModel(
    private val recordPresenter: RecordPresenter,
    private val SoundRawdataLiveDataFactory: SoundRawDataLiveDataFactory,
    private val soundFourierDataLiveDataFactory: SoundFourierDataLiveDataFactory
) : ViewModel() {

    val _soundRawDataLivedata = SoundRawdataLiveDataFactory.create(viewModelScope)
    val soundRawDataLiveData: LiveData<RawSoundDataViewEntity> = _soundRawDataLivedata

    val soundFrourierTransformLiveData = soundFourierDataLiveDataFactory.create(viewModelScope)

    fun startLoadingSoundRawData() {
        _soundRawDataLivedata.startLoadSoundRawData()
    }
}
