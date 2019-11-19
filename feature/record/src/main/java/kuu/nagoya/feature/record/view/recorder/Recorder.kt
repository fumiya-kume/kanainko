package kuu.nagoya.feature.record.view.recorder

import android.annotation.SuppressLint
import android.content.Context
import android.media.AudioRecord
import android.util.Log
import kuu.nagoya.waveparser.BitPerSample
import kuu.nagoya.waveparser.NumChannels
import kuu.nagoya.waveparser.SamplingRate
import kuu.nagoya.waveparser.WaveModel
import kotlin.math.abs


internal class Recorder(
    private val context: Context
) {

    private val file = context.externalMediaDirs?.first()
    private val filePath = "${file?.path}/output.wav"

    private val audioRecordWithWave: AudioRecordWithWave = AudioRecordWithWave(
        filePath,
        AudioSource.MIC,
        samplingRate = 9600,
        channelConfig = ChannelConfig.CHANNEL_IN_MONO,
        audioFormat = AudioFormat.ENCODING_PCM_16BIT
    )

    var onRecorderStatusUpdateListener: OnRecorderStatusUpdateListener? = null

    var recorderStatus: RecorderStatus =
        RecorderStatus.starting

    private fun updateRecorderStatus(status: RecorderStatus) {
        onRecorderStatusUpdateListener?.onStatusUpdated(status)
        recorderStatus = status
    }

    fun forceRecorderStusUpdate() {
        onRecorderStatusUpdateListener?.onStatusUpdated(recorderStatus)
    }

    private fun recorderErrorHappend(exception: Exception) {
        updateRecorderStatus(
            RecorderStatus.error(
                exception
            )
        )
        audioRecordWithWave.release()
    }

    init {
        initRecorder()
    }


    fun initRecorder() {
        updateRecorderStatus(RecorderStatus.starting)
    }

    fun startReocrding() {
        try {
            audioRecordWithWave.startRecording()
            updateRecorderStatus(RecorderStatus.recording)
        } catch (e: Exception) {
            recorderErrorHappend(e)
        }
    }

    @SuppressLint("NewApi")
    fun stopRecording() {
        try {
            audioRecordWithWave.stop()
            updateRecorderStatus(RecorderStatus.stopping)

            initRecorder()
        } catch (e: Exception) {
            recorderErrorHappend(e)
        }
    }

    fun maxAmplitude(): Int {
        return try {
            val data = audioRecordWithWave.currentAudioData
            return audioRecordWithWave.maxAmplitude

        } catch (e: java.lang.Exception) {
            0
        }
    }
}

class AudioRecordWithWave(
    private val filePath: String,
    val audioSource: AudioSource = AudioSource.DEFAULT,
    val samplingRate: Int = 44100,
    val channelConfig: ChannelConfig = ChannelConfig.CHANNEL_IN_MONO,
    val audioFormat: AudioFormat = AudioFormat.ENCODING_PCM_16BIT,
    val frameRate: Int = 10,
    val oneFrameDataCount: Int = samplingRate / frameRate,
    val oneFrameSizeInByte: Int = oneFrameDataCount * 2,
    val audioBufferSizeInByte: Int =
        Math.max(
            oneFrameSizeInByte * 100, // 適当に10フレーム分のバッファを持たせた
            getMinBufferSize(
                samplingRate,
                channelConfig.value,
                audioFormat.value
            )
        )
) : AudioRecord(
    audioSource.value,
    samplingRate,
    channelConfig.value,
    audioFormat.value,
    audioBufferSizeInByte
) {
    private val audioData: MutableList<Short> = mutableListOf()
    val currentAudioData: MutableList<Short> = mutableListOf()
    var maxAmplitude = 0

    private val bitPerSample =
        BitPerSample.of(
            when (audioFormat) {
                AudioFormat.ENCODING_PCM_16BIT -> BitPerSample.sixteenBit
                AudioFormat.ENCODING_PCM_8BIT -> BitPerSample.eightBit
                else -> BitPerSample.sixteenBit
            }
        )

    init {

        this.positionNotificationPeriod = oneFrameDataCount

        this.setRecordPositionUpdateListener(object : OnRecordPositionUpdateListener {
            override fun onMarkerReached(recorder: AudioRecord?) {

            }

            override fun onPeriodicNotification(recorder: AudioRecord?) {
                if (recorder == null) {
                    return
                }
                val data = ByteArray(oneFrameDataCount)
                recorder.read(data, 0, oneFrameDataCount)
                currentAudioData.clear()
                currentAudioData.addAll(data.asList().map { it.toShort() })

                maxAmplitude = currentAudioData
                    .map { it.toInt() }
                    .map { abs(it) }
                    .max() ?: 0

                Log.d("audio", maxAmplitude.toString())

                audioData += data.asList().map { it.toShort() }
            }
        })
    }

    override fun startRecording() {
        audioData.clear()

        super.startRecording()
    }

    override fun stop() {
        super.stop()

        WaveModel.storeToFile(
            filePath,
            audioData,
            NumChannels.of(channelConfig.value),
            bitPerSample,
            SamplingRate.of(samplingRate),
            true
        )
    }
}

enum class AudioFormat(
    val value: Int
) {
    ENCODING_INVALID(0),
    ENCODING_DEFAULT(1),
    ENCODING_PCM_16BIT(2),
    ENCODING_PCM_8BIT(3),
    ENCODING_PCM_FLOAT(4),
    ENCODING_AC3(5),
    ENCODING_E_AC3(6),
    ENCODING_DTS(7),
    ENCODING_DTS_HD(8),
    ENCODING_MP3(9),
    ENCODING_AAC_LC(10),
    ENCODING_AAC_HE_V1(11),
    ENCODING_AAC_HE_V2(12),
    ENCODING_IEC61937(13),
    ENCODING_DOLBY_TRUEHD(14),
    ENCODING_AAC_ELD(15),
    ENCODING_AAC_XHE(16),
    ENCODING_AC4(17),
    ENCODING_E_AC3_JOC(18),
    ENCODING_DOLBY_MAT(19),
}

enum class AudioSource(
    val value: Int
) {
    DEFAULT(0),
    MIC(1),
    VOICE_UPLINK(2),
    VOICE_DOWNLINK(3),
    VOICE_CALL(4),
    CAMCORDER(5),
    VOICE_RECOGNITION(6),
    VOICE_COMMUNICATION(7),
    REMOTE_SUBMIX(8),
    UNPROCESSED(9),
    VOICE_PERFORMANCE(10),
    ECHO_REFERENCE(1997),
    RADIO_TUNER(1998),
    HOTWORD(1999),
}

enum class ChannelConfig(val value: Int) {
    CHANNEL_IN_MONO(android.media.AudioFormat.CHANNEL_IN_FRONT),
    CHANNEL_IN_STEREO(android.media.AudioFormat.CHANNEL_IN_LEFT or android.media.AudioFormat.CHANNEL_IN_RIGHT)
}