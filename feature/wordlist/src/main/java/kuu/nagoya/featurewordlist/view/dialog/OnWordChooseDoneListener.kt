package kuu.nagoya.featurewordlist.view.dialog

import kuu.nagoya.featurewordlist.viewentity.WordViewEntity

internal interface OnWordChooseDoneListener {
    fun chooseDone(wordViewEntity: WordViewEntity)
}
