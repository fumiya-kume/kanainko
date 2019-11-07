package kuu.nagoya.feature.record.legacy.domain.repository

import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

internal class AudioRepositoryImpl : AudioRepository {

    private val rawSoundChannel = ConflatedBroadcastChannel<List<Float>>()
    override val audioFlow = rawSoundChannel.asFlow()
    override val audioMaxAmplitudeFlow = audioFlow.map { it.map { Math.abs(it) }.max() ?: 0.0F }

    private val samplingRate: Int = 44100
    private val channelConfig: Int = 1
    private val frameRate = 3
    private val oneFrameDataSize = samplingRate / frameRate

    private val frameBufferSize = oneFrameDataSize

//    private val frameBufferSize: Int = {
//        val minumunSize = Math.max(
//            oneFrameDataSize,
//            AudioRecord.getMinBufferSize(
//                samplingRate,
//                channelConfig,
//                AudioFormat.ENCODING_PCM_8BIT
//            )
//        )
//        minumunSize
//    }()

    private val audioRecord: AudioRecord = AudioRecord(
        MediaRecorder.AudioSource.MIC,
        samplingRate,
        channelConfig,
        AudioFormat.ENCODING_PCM_8BIT,
        frameBufferSize
    ).apply {
        this.positionNotificationPeriod = frameBufferSize
        this.setRecordPositionUpdateListener(
            object :
                AudioRecord.OnRecordPositionUpdateListener {
                override fun onMarkerReached(record: AudioRecord?) {
                }

                override fun onPeriodicNotification(record: AudioRecord?) {
                    val bufferList = ShortArray(frameBufferSize)

                    record?.read(bufferList, 0, frameBufferSize)

                    GlobalScope.launch {
                        Log.d("kanainko", "sended")
                        rawSoundChannel.send(
                            bufferList.map { it.toFloat() }.toList()
                        )
                    }
                }
            })
    }

    init {
        GlobalScope.launch(Dispatchers.IO) {
            while (audioRecord.state == AudioRecord.STATE_UNINITIALIZED) {
            }
            audioRecord.startRecording()
        }
    }
}