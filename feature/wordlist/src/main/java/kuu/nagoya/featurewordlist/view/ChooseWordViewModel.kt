package kuu.nagoya.featurewordlist.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kuu.nagoya.featurewordlist.usecase.StoreChoosedWordUsecase
import kuu.nagoya.featurewordlist.viewentity.WordViewEntity

internal class ChooseWordViewModel(
    wordListLiveDataFactory: WordListLiveDataFactory,
    private val storeChoosedWordUsecase: StoreChoosedWordUsecase
) : ViewModel() {
    val wordLisLiveData = wordListLiveDataFactory.create(viewModelScope)

    fun wordChoosed(wordViewEntity: WordViewEntity) {
        storeChoosedWordUsecase.storeWord(wordViewEntity)
    }
}
