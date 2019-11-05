package kuu.nagoya.entity.recordentity

fun String.toRecordName(): RecordName {
    return RecordName(this)
}