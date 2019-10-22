package jp.ac.daido.kanainko.record.view

import androidx.lifecycle.LiveData
import jp.ac.daido.kanainko.record.domain.usecase.LoadSoundRawDataUsecase
import jp.ac.daido.kanainko.record.view.viewentity.RawSoundDataViewEntity
import jp.ac.daido.kanainko.record.view.viewentity.convert
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

internal class SoundRawDataLiveData(
    private val coroutineScope: CoroutineScope,
    private val loadSoundRawDataUsecase: LoadSoundRawDataUsecase
) : LiveData<RawSoundDataViewEntity>() {

    fun startLoadSoundRawData() {
        coroutineScope.launch(Dispatchers.IO) {
            try {
                loadSoundRawDataUsecase
                    .execute()
                    .collect {
                        postValue(
                            it.convert()
                        )
                    }
            } catch (e: Exception) {
                print(e.toString())
            }
        }
    }
}