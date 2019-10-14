package jp.ac.daido.kanainko.graph

import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

internal class GraphViewModel : ViewModel() {

    private val samplingRate: Int = 44100
    private val channelConfig: Int = 1
    private val oneFrameDataSize = samplingRate / 2 * 50
    private val frameBufferSize = Math.max(
        oneFrameDataSize,
        AudioRecord.getMinBufferSize(
            samplingRate,
            channelConfig,
            AudioFormat.ENCODING_PCM_8BIT
        )
    ) / 50

    private val audioRecord: AudioRecord = AudioRecord(
        MediaRecorder.AudioSource.MIC,
        samplingRate,
        channelConfig,
        AudioFormat.ENCODING_PCM_8BIT,
        frameBufferSize
    ).apply {
        this.positionNotificationPeriod = frameBufferSize

        this.setRecordPositionUpdateListener(object :
            AudioRecord.OnRecordPositionUpdateListener {
            override fun onMarkerReached(record: AudioRecord?) {
                Log.d("audio_record", "maker attached")
            }

            override fun onPeriodicNotification(record: AudioRecord?) {
                Log.d("audio_record", "period attached")

                viewModelScope.launch(Dispatchers.IO) {
                    val bufferList = ShortArray(frameBufferSize)

                    record?.read(bufferList, 0, frameBufferSize)

                    this.launch(Dispatchers.Main) {
                        _soundVolumeLiveData.value = bufferList.map { it.toFloat() }
                        _soundRawFileLiveData.value = bufferList.map { it.toFloat() }
                    }
                }
            }
        })
    }

    init {
        audioRecord.startRecording()
    }

    private val _soundVolumeLiveData: MutableLiveData<List<Float>> = MutableLiveData()
    val soundVolumeLiveData: LiveData<List<Float>>
        get() = _soundVolumeLiveData

    private val _soundRawFileLiveData: MutableLiveData<List<Float>> = MutableLiveData()
    val soundRawLiveData: LiveData<List<Float>>
        get() = _soundRawFileLiveData
}