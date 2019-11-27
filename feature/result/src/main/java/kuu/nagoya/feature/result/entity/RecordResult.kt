package kuu.nagoya.feature.result.entity

internal data class RecordResult(
    val id: Int,
    val audioFilePath: String,
    val choosedWord: Char
)