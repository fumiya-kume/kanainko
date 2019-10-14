package jp.ac.daido.kanainko.graph.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.ac.daido.kanainko.graph.view.viewentity.SoundRawDataViewEntity
import jp.ac.daido.kanainko.graph.view.viewentity.SoundVolumeViewEntity

internal class GraphViewModel(
    private val soundVolumeLiveDataFactory: SoundVolumeLiveDataFactory,
    private val soundRawDataLiveDataFactory: SoundRawDataLiveDataFactory
) : ViewModel() {

//    private val samplingRate: Int = 44100
//    private val channelConfig: Int = 1
//    private val oneFrameDataSize = samplingRate / 2 * 50
//    private val frameBufferSize = Math.max(
//        oneFrameDataSize,
//        AudioRecord.getMinBufferSize(
//            samplingRate,
//            channelConfig,
//            AudioFormat.ENCODING_PCM_8BIT
//        )
//    ) / 50
//
//    private val audioRecord: AudioRecord = AudioRecord(
//        MediaRecorder.AudioSource.MIC,
//        samplingRate,
//        channelConfig,
//        AudioFormat.ENCODING_PCM_8BIT,
//        frameBufferSize
//    ).apply {
//        this.positionNotificationPeriod = frameBufferSize
//
//        this.setRecordPositionUpdateListener(object :
//            AudioRecord.OnRecordPositionUpdateListener {
//            override fun onMarkerReached(record: AudioRecord?) {
//                Log.d("audio_record", "maker attached")
//            }
//
//            override fun onPeriodicNotification(record: AudioRecord?) {
//                Log.d("audio_record", "period attached")
//
//                viewModelScope.launch(Dispatchers.IO) {
//                    val bufferList = ShortArray(frameBufferSize)
//
//                    record?.read(bufferList, 0, frameBufferSize)
//
//                    this.launch(Dispatchers.Main) {
//                        _soundVolumeLiveData.value = SoundVolumeViewEntity(
//                            0,
//                            bufferList.map { it.toFloat() }.average().toFloat()
//                        )
//                        _soundRawFileLiveData.value = SoundRawDataViewEntity(
//                            0,
//                            bufferList.map { it.toFloat() }
//                        )
//                    }
//                }
//            }
//        })
//    }

    init {

    }

    fun startRecording() {
//        audioRecord.startRecording()
        _soundVolumeLiveData = soundVolumeLiveDataFactory.create(viewModelScope)
        _soundRawFileLiveData = soundRawDataLiveDataFactory.create(viewModelScope)
    }


    private lateinit var _soundVolumeLiveData: SoundVolumeLiveData
    val soundVolumeLiveData: LiveData<SoundVolumeViewEntity>
        get() = _soundVolumeLiveData

    private lateinit var _soundRawFileLiveData: SoundRawDataLiveData
    val soundRawLiveData: LiveData<SoundRawDataViewEntity>
        get() = _soundRawFileLiveData
}