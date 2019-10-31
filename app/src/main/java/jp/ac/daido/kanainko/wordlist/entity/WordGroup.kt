package jp.ac.daido.kanainko.wordlist.entity

internal data class WordGroup(
    val id: Int,
    val displayName: String,
    val wordList: List<Word>
)