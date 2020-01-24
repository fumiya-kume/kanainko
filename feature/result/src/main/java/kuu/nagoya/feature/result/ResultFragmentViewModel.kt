package kuu.nagoya.feature.result

import android.graphics.Bitmap
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kuu.nagoya.feature.result.usecase.LearningedUsecase
import kuu.nagoya.feature.result.usecase.PlayModelVoiceUsecase
import kuu.nagoya.feature.result.usecase.PlayUserVoiceUsecase

internal class ResultFragmentViewModel(
    userChooseWordLiveDataFactory: UserChooseWordLiveDataFactory,
    PredictWordLiveDataFactory: PredictWordLiveDataFactory,
    modelVoiceSpectrogramLiveDataFactory: ModelVoiceSpectrogramLiveDataFactory,
    userVoiceSpectrogramLiveDataFactory: UserVoiceSpectrogramLiveDataFactory,
    private val playModelVoiceUsecase: PlayModelVoiceUsecase,
    private val playUserVoiceUsecase: PlayUserVoiceUsecase,
    private val resultNavigation: ResultNavigation,
    private val learningedUsecase: LearningedUsecase
) : ViewModel() {

    private var userChooseWordLiveData: UserChooseWordLiveData =
        userChooseWordLiveDataFactory.create()
    val userChooseWord: LiveData<String> = userChooseWordLiveData

    private val predictWordLiveData: PredictWordLiveData =
        PredictWordLiveDataFactory.create(viewModelScope)
    val predictWord: LiveData<String> = predictWordLiveData

    private val modelVoiceSpectrogramLiveData: ModelVoiceSpectrogramLiveData =
        modelVoiceSpectrogramLiveDataFactory.create(viewModelScope)
    val modelVoiceSpectrogramBitmap: LiveData<Bitmap> = modelVoiceSpectrogramLiveData

    private val userVoiceSpectrogramLiveData: UserVoiceSpectrogramLiveData =
        userVoiceSpectrogramLiveDataFactory.create(viewModelScope)
    val userVoiceSpectrogramBitmap: LiveData<Bitmap> = userVoiceSpectrogramLiveData

    init {
        learningedUsecase.execute()
    }

    fun playModelVoice() {
        viewModelScope.launch {
            playModelVoiceUsecase.execute()
        }
    }

    fun playUSerVoice() {
        viewModelScope.launch {
            playUserVoiceUsecase.exeute()
        }
    }

    fun navigateWordList(fragment: Fragment) {
        resultNavigation.navigateWordChooseScreen(fragment)
    }
}
