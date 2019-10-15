package jp.ac.daido.kanainko.graph.domain.repository

import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch


internal class AudioRepositoryImpl(
) : AudioRepository {
    override suspend fun startAudioRecord() {
        audioRecord.startRecording()
    }

    private val audioDataChannel = Channel<List<Float>>()

    override suspend fun loadAudioData(): Flow<List<Float>> {
        return audioDataChannel.consumeAsFlow()
    }

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

            }

            override fun onPeriodicNotification(record: AudioRecord?) {
                val bufferList = ShortArray(frameBufferSize)

                record?.read(bufferList, 0, frameBufferSize)

                GlobalScope.launch {
                    audioDataChannel.send(
                        bufferList.map { it.toFloat() }.toList()
                    )
                }
            }
        })
    }

}