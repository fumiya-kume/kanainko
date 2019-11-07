package kuu.nagoya.feature.record.view

import android.content.Context
import android.media.MediaRecorder

sealed class RecorderStatus {
    object starting : RecorderStatus()
    object ready : RecorderStatus()
    object recording : RecorderStatus()
    object stopping : RecorderStatus()

    data class error(val exception: java.lang.Exception) : RecorderStatus()
}

internal interface OnRecorderStatusUpdateListener {
    fun onStatusUpdated(status: RecorderStatus): Unit
}

internal class Recorder(
    private val context: Context,
    private val mediaPath: String? = null
) {

    private val mediaRecorder = MediaRecorder()

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
        updateRecorderStatus(RecorderStatus.error(exception))
        mediaRecorder.release()
    }

    init {
        initRecorder()
    }

    fun initRecorder() {
        updateRecorderStatus(RecorderStatus.starting)

        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC)

        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.AAC_ADTS)

        val file = context.externalMediaDirs?.first()
        val filePath = "${file?.path}/output.aac"

        val savePath = mediaPath ?: filePath
        mediaRecorder.setOutputFile(savePath)

        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
        try {
            mediaRecorder.prepare()
            updateRecorderStatus(RecorderStatus.ready)
        } catch (e: Exception) {
            recorderErrorHappend(e)
        }
    }

    fun startReocrding() {
        try {
            mediaRecorder.start()
            updateRecorderStatus(RecorderStatus.recording)
        } catch (e: Exception) {
            recorderErrorHappend(e)
        }
    }

    fun stopRecording() {
        try {
            mediaRecorder.stop()
            updateRecorderStatus(RecorderStatus.stopping)
            initRecorder()
        } catch (e: Exception) {
            recorderErrorHappend(e)
        }
    }

    fun maxAmplitude(): Int {
        try {
            return mediaRecorder.maxAmplitude
        } catch (e: java.lang.Exception) {
            return 0
        }
    }
}