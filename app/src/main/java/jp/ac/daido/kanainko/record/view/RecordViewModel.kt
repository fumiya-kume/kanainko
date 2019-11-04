package jp.ac.daido.kanainko.record.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.ac.daido.kanainko.record.domain.repository.AudioRepository
import jp.ac.daido.kanainko.record.viewentity.AudioVolumeViewEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.launch
import java.util.Timer
import kotlin.concurrent.schedule

internal class RecordViewModel(
    private val audioRepository: AudioRepository
) : ViewModel() {

    suspend fun startRecordVolume(): LiveData<AudioVolumeViewEntity> {
//        return audioRepository
//            .audioFlow
//            .flowOn(Dispatchers.IO)
//            .mapIndexed { index, value ->
//                Log.d("output", value.elementAtOrNull(0).toString())
//                AudioVolumeViewEntity.of(
//                    index,
//                    value
//                )
//            }

        val flow = MutableLiveData<AudioVolumeViewEntity>()

        viewModelScope.launch(Dispatchers.IO) {
            var counter = 0
            Timer().schedule(0, 1000 / 60) {
                counter += 1
                flow.postValue(
                    AudioVolumeViewEntity(
                        this.scheduledExecutionTime().toInt(),
                        counter.toDouble()
                    )
                )
            }
        }

        return flow
    }

    fun flowData(): kotlinx.coroutines.flow.Flow<Double> {
        val flow = ConflatedBroadcastChannel<Double>()
        Timer().schedule(0, 1000 / 60) {
            flow.offer(Math.random())
        }
        return flow.asFlow()
    }

    fun loadData(): Double {
        return Math.random()
    }
}
