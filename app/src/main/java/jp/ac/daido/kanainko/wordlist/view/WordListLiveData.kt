package jp.ac.daido.kanainko.wordlist.view

import androidx.lifecycle.LiveData
import jp.ac.daido.kanainko.wordlist.usecase.WordListReadonlyRepository
import jp.ac.daido.kanainko.wordlist.viewentity.WordGroupViewEntity
import jp.ac.daido.kanainko.wordlist.viewentity.convert
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

internal class WordListLiveData(
    private val coroutineScope: CoroutineScope,
    private val wordListReadonlyUsecase: WordListReadonlyRepository
) : LiveData<List<WordGroupViewEntity>>() {
    init {
        coroutineScope
            .launch(Dispatchers.IO) {
                postValue(
                    wordListReadonlyUsecase
                        .loadWordGroup()
                        .convert()
                )
            }
    }
}