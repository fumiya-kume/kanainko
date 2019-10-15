package jp.ac.daido.kanainko.graph.view

import androidx.lifecycle.LiveData
import jp.ac.daido.kanainko.graph.domain.usecase.LoadSoundRawDataUsecase
import jp.ac.daido.kanainko.graph.view.viewentity.SoundRawDataViewEntity
import jp.ac.daido.kanainko.graph.view.viewentity.convert
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

internal class SoundRawDataLiveData(
    private val coroutineScope: CoroutineScope,
    private val soundRawDataUsecase: LoadSoundRawDataUsecase
) : LiveData<SoundRawDataViewEntity>() {

    init {
        coroutineScope.launch(Dispatchers.IO) {
            soundRawDataUsecase
                .execute()
                .collect {
                    postValue(
                        it.convert()
                    )
                }
        }
    }
}