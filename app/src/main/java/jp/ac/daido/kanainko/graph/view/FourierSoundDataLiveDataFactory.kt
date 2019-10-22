package jp.ac.daido.kanainko.graph.view

import jp.ac.daido.kanainko.record.domain.usecase.FourierTransformUsecase
import jp.ac.daido.kanainko.record.domain.usecase.LoadSoundRawDataUsecase
import kotlinx.coroutines.CoroutineScope

internal class FourierSoundDataLiveDataFactory(
    private val loadSoundRawDataUsecase: LoadSoundRawDataUsecase,
    private val fourierTransformUsecase: FourierTransformUsecase
) {
    fun create(
        coroutineScope: CoroutineScope
    ): FourierSoundDataLiveData {
        return FourierSoundDataLiveData(
            coroutineScope,
            loadSoundRawDataUsecase,
            fourierTransformUsecase
        )
    }
}