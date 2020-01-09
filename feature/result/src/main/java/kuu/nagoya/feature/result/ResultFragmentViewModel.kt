package kuu.nagoya.feature.result

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kuu.nagoya.feature.result.service.AssetsService
import kuu.nagoya.feature.result.service.FourieService
import kuu.nagoya.feature.result.service.PlayAudioService
import kuu.nagoya.feature.result.usecase.LoadRecordResultUsecase
import kuu.nagoya.waveparser.WaveParse
import java.io.File

internal class ResultFragmentViewModel(
    private val loadRecordResultUsecase: LoadRecordResultUsecase,
    private val fourieService: FourieService,
    private val audioService: PlayAudioService,
    private val assetsService: AssetsService
) : ViewModel() {

    private val chooseWordMutableLiveData: MutableLiveData<Char> = MutableLiveData()
    val chooseWordLiveData = chooseWordMutableLiveData

    private val audioFilePathMutableLiveData: MutableLiveData<String> = MutableLiveData()
    val audioFilePathLiveData = audioFilePathMutableLiveData

    private val recordSpectrogramBitmapMutableLiveData: MutableLiveData<Bitmap> = MutableLiveData()
    val recordSpectrogramBitmapLivedata: LiveData<Bitmap> = recordSpectrogramBitmapMutableLiveData

    init {
        audioFilePathLiveData
            .observeForever {
                viewModelScope.launch(Dispatchers.IO) {
                    val audioData = WaveParse.loadWaveFromFile(File(it)).data
                    val bitmap = fourieService.audioDataToImage(audioData)
                    recordSpectrogramBitmapMutableLiveData.postValue(bitmap)
                }
            }
    }

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

    fun playModelAudio() {
        viewModelScope.launch {
            val modelAudioPath = assetsService.assetFileDescriptor("model_1.wav")
            audioService.playAudio(modelAudioPath)
        }
    }
}
