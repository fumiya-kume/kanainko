package kuu.nagoya.featurewordlist.viewentity

import kuu.nagoya.featurewordlist.entity.Word

internal fun Word.convert(): WordViewEntity =
    WordViewEntity(
        id = this.id,
        name = this.name,
        alphabetName = this.alphabet,
        isChoose = false,
        isLearned = false
    )
