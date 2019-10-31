package jp.ac.daido.kanainko.wordlist.viewentity

internal data class WordViewEntity(
    val id: Int,
    val name: String,
    val alphabetName: String,
    var isChoose: Boolean = false
)