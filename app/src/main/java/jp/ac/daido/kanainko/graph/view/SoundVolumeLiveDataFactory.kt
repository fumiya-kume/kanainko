package jp.ac.daido.kanainko.graph.view

import jp.ac.daido.kanainko.graph.domain.usecase.LoadSoundVolumeUsecase
import kotlinx.coroutines.CoroutineScope

internal class SoundVolumeLiveDataFactory(
    private val loadSoundVolumeUsecase: LoadSoundVolumeUsecase
) {
    fun create(coroutineScope: CoroutineScope): SoundVolumeLiveData {
        return SoundVolumeLiveData(coroutineScope, loadSoundVolumeUsecase)
    }
}