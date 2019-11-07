package kuu.nagoya.feature.record.domain.recorder

sealed class RecorderStatus {
    object starting : RecorderStatus()
    object ready : RecorderStatus()
    object recording : RecorderStatus()
    object stopping : RecorderStatus()

    data class error(val exception: java.lang.Exception) : RecorderStatus()
}