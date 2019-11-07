package kuu.nagoya.feature.record.domain.recorder

internal interface OnRecorderStatusUpdateListener {
    fun onStatusUpdated(status: RecorderStatus): Unit
}