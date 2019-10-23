package jp.ac.daido.kanainko.record.view

import androidx.lifecycle.LiveData
import jp.ac.daido.kanainko.record.domain.usecase.FourierTransformUsecase
import jp.ac.daido.kanainko.record.domain.usecase.LoadSoundRawDataUsecase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal class SoundFourierDataLiveData(
    private val coroutineScope: CoroutineScope,
    private val rawSoundDataUsecase: LoadSoundRawDataUsecase,
    private val frourierTransformUsecase: FourierTransformUsecase
) : LiveData<List<Float>>() {
    init {
        coroutineScope.launch(Dispatchers.IO) {
            rawSoundDataUsecase
                .execute()
                .map {
                    withContext(Dispatchers.Unconfined) {
                        frourierTransformUsecase.execute(it).data
                    }
                }
                .collect {
                    coroutineScope.launch(Dispatchers.Main) { value = it }
                }
        }
    }
}