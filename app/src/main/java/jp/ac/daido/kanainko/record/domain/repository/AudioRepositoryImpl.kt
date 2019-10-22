package jp.ac.daido.kanainko.record.domain.repository

import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.launch

internal class AudioRepositoryImpl : AudioRepository {

    private val rawSoundChannel = ConflatedBroadcastChannel<List<Float>>()
    override val audioFlow = rawSoundChannel.asFlow()

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
    ) / 100

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
        audioRecord.startRecording()
    }
}