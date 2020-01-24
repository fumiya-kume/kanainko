package kuu.nagoya.featurewordlist.view

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kuu.nagoya.featurewordlist.usecase.WordListReadonlyRepository
import kuu.nagoya.featurewordlist.viewentity.WordGroupViewEntity
import kuu.nagoya.featurewordlist.viewentity.convert
import nagoya.kuu.learning_data.LearningDataRepository

internal class WordListLiveData(
    private val coroutineScope: CoroutineScope,
    private val wordListReadonlyUsecase: WordListReadonlyRepository,
    private val learningDataRepository: LearningDataRepository
) : LiveData<List<WordGroupViewEntity>>() {
    init {
        coroutineScope
            .launch(Dispatchers.IO) {
                val wordList = wordListReadonlyUsecase
                    .loadWordGroup()
                    .convert()

                postValue(
                    wordList.map {
                        it.copy(
                            wordList =
                            it.wordList.map {
                                it.copy(
                                    isLearned = learningDataRepository.isLearnined(it.id)
                                )
                            }
                        )
                    }
                )
            }
    }
}
