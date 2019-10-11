package jp.ac.daido.kanainko.record.domain.service

import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import android.util.Log
import jp.ac.daido.kanainko.record.domain.AudioService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

internal class AudioServiceImpl : AudioService {
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

    override suspend fun load(): Flow<List<Float>> =
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
}