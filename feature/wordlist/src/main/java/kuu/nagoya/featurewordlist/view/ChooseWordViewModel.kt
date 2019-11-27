package kuu.nagoya.featurewordlist.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

internal class ChooseWordViewModel(
    private val wordListLiveDataFactory: WordListLiveDataFactory
) : ViewModel() {
    val wordLisLiveData = wordListLiveDataFactory.create(viewModelScope)
}