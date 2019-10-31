package jp.ac.daido.kanainko.wordlist.view

import jp.ac.daido.kanainko.wordlist.usecase.WordListReadonlyRepository
import kotlinx.coroutines.CoroutineScope

internal class WordListLiveDataFactory(
    private val wordListReadonlyRepository: WordListReadonlyRepository
) {
    fun create(
        coroutineScope: CoroutineScope
    ): WordListLiveData{
        return WordListLiveData(
            coroutineScope,
            wordListReadonlyRepository
        )
    }
}