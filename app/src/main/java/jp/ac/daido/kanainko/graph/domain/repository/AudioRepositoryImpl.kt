package jp.ac.daido.kanainko.graph.domain.repository

import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.launch

internal class AudioRepositoryImpl() : AudioRepository {
    override suspend fun startAudioRecord() {
        audioRecord.startRecording()
    }

    private val audioFlow = channelFlow {
        val producerScode = this
        val recordPositionUpdateListener = object :
            AudioRecord.OnRecordPositionUpdateListener {
            override fun onMarkerReached(record: AudioRecord?) {
            }

            override fun onPeriodicNotification(record: AudioRecord?) {
                val bufferList = ShortArray(frameBufferSize)

                record?.read(bufferList, 0, frameBufferSize)

                GlobalScope.launch {
                    producerScode.send(
                        bufferList.map { it.toFloat() }.toList()
                    )
                }
            }
        }

        audioRecord.setRecordPositionUpdateListener(recordPositionUpdateListener)
    }

    override suspend fun loadAudioData(): Flow<List<Float>> = audioFlow

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
    ) / 5000

    private val audioRecord: AudioRecord = AudioRecord(
        MediaRecorder.AudioSource.MIC,
        samplingRate,
        channelConfig,
        AudioFormat.ENCODING_PCM_8BIT,
        frameBufferSize
    ).apply {
        this.positionNotificationPeriod = frameBufferSize
    }
}
