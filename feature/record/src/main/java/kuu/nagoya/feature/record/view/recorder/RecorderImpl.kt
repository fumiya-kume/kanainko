package kuu.nagoya.feature.record.view.recorder

import android.content.Context
import android.media.AudioFormat
import android.media.MediaRecorder
import omrecorder.AudioRecordConfig
import omrecorder.OmRecorder
import omrecorder.PullTransport
import omrecorder.PullTransport.OnAudioChunkPulledListener
import omrecorder.PullableSource
import java.io.File


internal class RecorderImpl(
    context: Context
) : Recorder {

    private fun mic(): PullableSource {
        return PullableSource.Default(
            AudioRecordConfig.Default(
                MediaRecorder.AudioSource.MIC, AudioFormat.ENCODING_PCM_16BIT,
                AudioFormat.CHANNEL_IN_MONO, 44100
            )
        )
    }

    override var outputFilePath = context.externalMediaDirs.first().absolutePath + "/output.wav"

    private var omRecorder: omrecorder.Recorder? = null

    override var onRecorderStatusUpdateListener: OnRecorderStatusUpdateListener? = null

    override var recorderStatus: RecorderStatus = RecorderStatus.ready
        set(value) {
            onRecorderStatusUpdateListener?.onStatusUpdated(value)
            field = value
        }

    private var maxAmplitube: Double = 0.0

    override fun start() {

        omRecorder = OmRecorder.wav(
            PullTransport.Default(mic(),
                OnAudioChunkPulledListener { audioChunk ->
                    maxAmplitube = audioChunk.maxAmplitude()
                }),
            File(outputFilePath)
        )

        omRecorder?.startRecording()

        recorderStatus = RecorderStatus.recording
    }

    override fun stop() {
        omRecorder?.stopRecording()

        recorderStatus = RecorderStatus.stopping
    }

    override fun getMaxAmplitude(): Double {
        return maxAmplitube
    }
}