package jp.ac.daido.kanainko.wordlist.view.dialog

import jp.ac.daido.kanainko.wordlist.viewentity.WordViewEntity

internal interface OnWordChooseListener {
    fun choose(wordViewEntity: WordViewEntity): Unit
}