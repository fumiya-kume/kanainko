package jp.ac.daido.kanainko.wordlist.viewentity

import jp.ac.daido.kanainko.wordlist.entity.Word

internal fun Word.convert(): WordViewEntity =
    WordViewEntity(
        this.id,
        this.name,
        this.alphabet
    )