package kuu.nagoya.featurewordlist.view

import kotlinx.coroutines.CoroutineScope
import kuu.nagoya.featurewordlist.usecase.WordListReadonlyRepository

internal class WordListLiveDataFactory(
    private val wordListReadonlyRepository: WordListReadonlyRepository
) {
    fun create(
        coroutineScope: CoroutineScope
    ): WordListLiveData {
        return WordListLiveData(
            coroutineScope,
            wordListReadonlyRepository
        )
    }
}
