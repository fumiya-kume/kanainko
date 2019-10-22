package jp.ac.daido.kanainko.graph.view

import jp.ac.daido.kanainko.record.domain.usecase.LoadSoundRawDataUsecase
import kotlinx.coroutines.CoroutineScope

internal class SoundRawDataLiveDataFactory(
    private val soundRawDataUsecase: LoadSoundRawDataUsecase
) {
    fun create(coroutineScope: CoroutineScope): SoundRawDataLiveData = SoundRawDataLiveData(
        coroutineScope,
        soundRawDataUsecase
    )
}
