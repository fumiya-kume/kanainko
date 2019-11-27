package kuu.nagoya.featurewordlist.viewentity

internal data class WordGroupViewEntity(
    val Id: Int,
    val name: String,
    val wordList: List<WordViewEntity>
)