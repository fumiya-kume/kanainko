package kuu.nagoya.featurewordlist.view

import kuu.nagoya.featurewordlist.viewentity.WordGroupViewEntity

internal interface OnWordGruopClickListener {
    fun click(wordGroupViewEntity: WordGroupViewEntity): Unit
}