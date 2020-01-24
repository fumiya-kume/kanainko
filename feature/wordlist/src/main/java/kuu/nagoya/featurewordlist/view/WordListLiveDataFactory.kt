package kuu.nagoya.featurewordlist.view

import kotlinx.coroutines.CoroutineScope
import kuu.nagoya.featurewordlist.usecase.WordListReadonlyRepository
import nagoya.kuu.learning_data.LearningDataRepository

internal class WordListLiveDataFactory(
    private val wordListReadonlyRepository: WordListReadonlyRepository,
    private val learningDataRepository: LearningDataRepository
) {
    fun create(
        coroutineScope: CoroutineScope
    ): WordListLiveData {
        return WordListLiveData(
            coroutineScope,
            wordListReadonlyRepository,
            learningDataRepository
        )
    }
}
