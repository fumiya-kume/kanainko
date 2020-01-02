package kuu.nagoya.dashboard.view

import kuu.nagoya.dashboard.viewentity.RecordViewEntity

internal interface OnMusicListItemClickListener {
    fun clicked(musicItem: RecordViewEntity)
}
