package jp.ac.daido.kanainko.record.view

import jp.ac.daido.kanainko.record.domain.usecase.LoadSoundRawDataUsecase
import kotlinx.coroutines.CoroutineScope

internal class SoundRawDataLiveDataFactory(
    private val soundRawDataUsecase: LoadSoundRawDataUsecase
) {
    fun create(
        coroutineSCope: CoroutineScope
    ): SoundRawDataLiveData {
        return SoundRawDataLiveData(
            coroutineSCope,
            soundRawDataUsecase
        )
    }
}