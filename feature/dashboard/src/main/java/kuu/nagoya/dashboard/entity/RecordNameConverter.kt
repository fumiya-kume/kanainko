package kuu.nagoya.dashboard.entity

fun String.toRecordName(): RecordName {
    return RecordName(this)
}