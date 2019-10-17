package jp.ac.daido.kanainko.graph.view

import androidx.lifecycle.LiveData
import jp.ac.daido.kanainko.graph.domain.usecase.LoadSoundVolumeUsecase
import jp.ac.daido.kanainko.graph.view.viewentity.SoundVolumeViewEntity
import jp.ac.daido.kanainko.graph.view.viewentity.convert
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

internal class SoundVolumeLiveData(
    private val coroutineScope: CoroutineScope,
    private val loadSoundVolumeUsecase: LoadSoundVolumeUsecase
) : LiveData<SoundVolumeViewEntity>() {

    init {
        coroutineScope.launch(Dispatchers.IO) {
            coroutineScope.launch {
                loadSoundVolumeUsecase
                    .execute()
                    .collect {
                        postValue(it.convert())
                    }
            }
        }
    }
}
