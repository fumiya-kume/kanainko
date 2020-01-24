package kuu.nagoya.featurewordlist.viewentity

data class WordViewEntity(
    val id: Int,
    val name: String,
    val alphabetName: String,
    var isChoose: Boolean = false,
    val isLearned: Boolean
)
