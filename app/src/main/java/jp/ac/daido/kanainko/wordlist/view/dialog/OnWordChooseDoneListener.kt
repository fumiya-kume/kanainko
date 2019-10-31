package jp.ac.daido.kanainko.wordlist.view.dialog

import jp.ac.daido.kanainko.wordlist.viewentity.WordViewEntity

internal interface OnWordChooseDoneListener {
    fun chooseDone(wordViewEntity: WordViewEntity)
}