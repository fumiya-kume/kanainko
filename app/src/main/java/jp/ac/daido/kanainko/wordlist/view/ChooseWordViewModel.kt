package jp.ac.daido.kanainko.wordlist.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

internal class ChooseWordViewModel(
    private val wordListLiveDataFactory: WordListLiveDataFactory
) : ViewModel() {
    val wordLisLiveData = wordListLiveDataFactory.create(viewModelScope)
}