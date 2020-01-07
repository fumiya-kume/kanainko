package kuu.nagoya.feature.result

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kuu.nagoya.feature.result.service.PlayAudioService
import kuu.nagoya.feature.result.usecase.LoadRecordResultUsecase

internal class ResultFragmentViewModel(
    private val loadRecordResultUsecase: LoadRecordResultUsecase,
    private val audioService: PlayAudioService
) : ViewModel() {

    private val chooseWordMutableLiveData: MutableLiveData<Char> = MutableLiveData()
    val chooseWordLiveData = chooseWordMutableLiveData

    private val audioFilePathMutableLiveData: MutableLiveData<String> = MutableLiveData()
    val audioFilePathLiveData = audioFilePathMutableLiveData

    fun load() {
        viewModelScope.launch {
            val result = loadRecordResultUsecase.execute()
            chooseWordMutableLiveData.postValue(result.choosedWord)
            audioFilePathMutableLiveData.postValue(result.audioFilePath)
        }
    }

    fun playRecordAudio() {
        if (audioFilePathLiveData.value.isNullOrEmpty()) {
            return
        }
        viewModelScope.launch {
            audioService.playAudio(audioFilePathLiveData.value!!)
        }
    }
}
