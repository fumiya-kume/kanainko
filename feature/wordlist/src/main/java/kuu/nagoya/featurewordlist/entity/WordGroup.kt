package kuu.nagoya.featurewordlist.entity

internal data class WordGroup(
    val id: Int,
    val displayName: String,
    val wordList: List<Word>
)
