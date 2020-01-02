package kuu.nagoya.featurewordlist.view

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kuu.nagoya.featurewordlist.usecase.WordListReadonlyRepository
import kuu.nagoya.featurewordlist.viewentity.WordGroupViewEntity
import kuu.nagoya.featurewordlist.viewentity.convert

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
