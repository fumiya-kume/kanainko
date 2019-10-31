package jp.ac.daido.kanainko.wordlist.view

import jp.ac.daido.kanainko.wordlist.viewentity.WordGroupViewEntity

internal interface OnWordGruopClickListener {
    fun click(wordGroupViewEntity: WordGroupViewEntity): Unit
}