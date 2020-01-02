package kuu.nagoya.featurewordlist.usecase

import kuu.nagoya.featurewordlist.entity.WordGroup

internal interface WordListReadonlyRepository {
    suspend fun loadWordGroup(): List<WordGroup>
}
