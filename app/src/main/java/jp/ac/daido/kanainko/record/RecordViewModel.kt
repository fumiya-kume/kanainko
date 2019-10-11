package jp.ac.daido.kanainko.record

import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.ac.daido.kanainko.record.domain.AudioService
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlin.coroutines.CoroutineContext

internal class RecordViewModel(
    private val recordPresenter: RecordPresenter,
    private val audioService: AudioService
) : ViewModel(), CoroutineScope {

    private val audioDataBuffer = emptyList<Float>()

    private val job = Job()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

    private val _volumeLiveData = MutableLiveData<Float>()

    val volumeLiveData: LiveData<Float> = _volumeLiveData

    var audioRecord: AudioRecord

    val samplingRate: Int = 44100
    val channelConfig: Int = 1
    val oneFrameDataSize = samplingRate / 2 * 50
    val frameBufferSize = Math.max(
        oneFrameDataSize + 10,
        AudioRecord.getMinBufferSize(
            samplingRate,
            channelConfig,
            AudioFormat.ENCODING_PCM_8BIT
        )
    )

    init {
        audioRecord = AudioRecord(
            MediaRecorder.AudioSource.MIC,
            samplingRate,
            channelConfig,
            AudioFormat.ENCODING_PCM_8BIT,
            frameBufferSize
        )
    }

    fun load(): Flow<List<Float>> =
        flow {
            val period = frameBufferSize / 10000
            audioRecord.positionNotificationPeriod = period

            val bufferList = ShortArray(frameBufferSize)
            audioRecord.setRecordPositionUpdateListener(object :
                AudioRecord.OnRecordPositionUpdateListener {
                override fun onMarkerReached(record: AudioRecord?) {
                    Log.d("audio_record", "maker attached")
                }

                override fun onPeriodicNotification(record: AudioRecord?) {
                    Log.d("audio_record", "period attached")

                    record?.read(bufferList, 0, frameBufferSize / 100000)
                    GlobalScope.launch { emit(bufferList.map { it.toFloat() }) }
                }
            })

            audioRecord.startRecording()
        }

    init {
//        viewModelScope.launch(Dispatchers.IO) {
//            audioService.load()
//                .collect {
//                    //                    (audioDataBuffer as MutableList).add(it)
////                    if (audioDataBuffer.size > 10000) {
////                        viewModelScope.launch(Dispatchers.Main) {
////                            _volumeLiveData.postValue(audioDataBuffer.max())
////                        }
////                        audioDataBuffer.clear()
////                    }
//
////                    viewModelScope.launch(Dispatchers.IO) {
////                        _volumeLiveData.postValue(it)
////                    }
//
//                    GlobalScope.launch { _volumeLiveData.postValue(it.max()) }
//                }
//        }

    }

    fun start() {

        viewModelScope.launch {
            load().collect {
                println(it)
            }
        }

    }
}