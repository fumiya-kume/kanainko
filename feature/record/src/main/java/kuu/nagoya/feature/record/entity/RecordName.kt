package kuu.nagoya.feature.record.entity

internal data class RecordName(
    val name: String
)

internal fun String.toRecordName(): RecordName {
    return RecordName((this))
}