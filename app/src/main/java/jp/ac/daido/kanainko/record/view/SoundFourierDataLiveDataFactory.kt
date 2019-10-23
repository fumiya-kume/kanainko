package jp.ac.daido.kanainko.record.view

import jp.ac.daido.kanainko.record.domain.usecase.FourierTransformUsecase
import jp.ac.daido.kanainko.record.domain.usecase.LoadSoundRawDataUsecase
import kotlinx.coroutines.CoroutineScope

internal class SoundFourierDataLiveDataFactory(
    private val loadSoundRawDataUsecase: LoadSoundRawDataUsecase,
    private val fourierTransformUsecase: FourierTransformUsecase
) {
    fun create(
        coroutineScope: CoroutineScope
    ): SoundFourierDataLiveData {
        return SoundFourierDataLiveData(
            coroutineScope,
            loadSoundRawDataUsecase,
            fourierTransformUsecase
        )
    }
}