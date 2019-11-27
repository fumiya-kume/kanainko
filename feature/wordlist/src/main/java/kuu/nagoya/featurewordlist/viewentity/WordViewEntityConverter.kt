package kuu.nagoya.featurewordlist.viewentity

import kuu.nagoya.featurewordlist.entity.Word

internal fun Word.convert(): WordViewEntity =
    WordViewEntity(
        this.id,
        this.name,
        this.alphabet
    )