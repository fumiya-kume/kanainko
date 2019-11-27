package kuu.nagoya.feature.record.entity

internal data class RecordName(
    val fileName: String
)

internal fun String.toRecordName(): RecordName {
    return RecordName((this))
}