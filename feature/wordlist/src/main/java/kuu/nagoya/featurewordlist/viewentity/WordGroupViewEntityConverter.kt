package kuu.nagoya.featurewordlist.viewentity

import kuu.nagoya.featurewordlist.entity.WordGroup

internal fun WordGroup.convert(): WordGroupViewEntity =
    WordGroupViewEntity(
        this.id,
        this.displayName,
        this.wordList.map { it.convert() }
    )

internal fun List<WordGroup>.convert(): List<WordGroupViewEntity> = this.map { it.convert() }
