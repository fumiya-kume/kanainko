package kuu.nagoya.featurewordlist.view.dialog

import kuu.nagoya.featurewordlist.viewentity.WordViewEntity

internal interface OnWordChooseListener {
    fun choose(wordViewEntity: WordViewEntity): Unit
}
