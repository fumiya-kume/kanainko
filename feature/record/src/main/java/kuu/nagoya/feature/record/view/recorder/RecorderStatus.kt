package kuu.nagoya.feature.record.view.recorder

sealed class RecorderStatus {
    object ready : RecorderStatus()
    object recording : RecorderStatus()
    object stopping : RecorderStatus()

    data class error(val exception: java.lang.Exception) : RecorderStatus()
}
