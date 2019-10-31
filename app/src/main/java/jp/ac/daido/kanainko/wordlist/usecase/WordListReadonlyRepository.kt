package jp.ac.daido.kanainko.wordlist.usecase

import jp.ac.daido.kanainko.wordlist.entity.WordGroup

internal interface WordListReadonlyRepository {
    suspend fun loadWordGroup(): List<WordGroup>
}