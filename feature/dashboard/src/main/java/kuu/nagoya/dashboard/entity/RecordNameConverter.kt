package kuu.nagoya.dashboard.entity

internal fun String.toRecordName(): RecordName {
    return RecordName(this)
}