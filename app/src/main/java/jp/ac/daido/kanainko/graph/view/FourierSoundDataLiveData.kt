package jp.ac.daido.kanainko.graph.view

import androidx.lifecycle.LiveData
import jp.ac.daido.kanainko.graph.domain.usecase.FourierTransformUsecase
import jp.ac.daido.kanainko.graph.domain.usecase.LoadSoundRawDataUsecase
import jp.ac.daido.kanainko.graph.view.viewentity.FourierSoundDataViewEntity
import jp.ac.daido.kanainko.graph.view.viewentity.convert
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.withIndex
import kotlinx.coroutines.launch

internal class FourierSoundDataLiveData internal constructor(
    coroutineScope: CoroutineScope,
    private val loadSoundRawDataUsecase: LoadSoundRawDataUsecase,
    private val fourierTransformUsecase: FourierTransformUsecase
) : LiveData<FourierSoundDataViewEntity>() {
    init {
        coroutineScope.launch(Dispatchers.Unconfined) {
            loadSoundRawDataUsecase
                .execute()
                .withIndex()
                .collect {
                    postValue(
                        fourierTransformUsecase.execute(it.value).convert(it.index)
                    )
                }
        }
    }
}