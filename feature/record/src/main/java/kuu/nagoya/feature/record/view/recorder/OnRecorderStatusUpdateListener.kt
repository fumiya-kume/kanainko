package kuu.nagoya.feature.record.view.recorder

internal interface OnRecorderStatusUpdateListener {
    fun onStatusUpdated(status: RecorderStatus)
}
