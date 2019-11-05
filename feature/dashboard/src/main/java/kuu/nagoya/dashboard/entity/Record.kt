package kuu.nagoya.dashboard.entity

internal data class Record(
    val id: Int,
    val filePath: FilePath,
    val recordName
    : RecordName
)

