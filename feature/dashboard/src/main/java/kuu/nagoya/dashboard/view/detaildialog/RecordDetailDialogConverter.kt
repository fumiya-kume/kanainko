package kuu.nagoya.dashboard.view.detaildialog

import kuu.nagoya.dashboard.viewentity.RecordViewEntity

internal fun RecordViewEntity.convert(): RecordDetailDialogViewEntity {
    return RecordDetailDialogViewEntity(
        this.id,
        this.title,
        this.audioFilePath,
        "FilePath: ${this.audioFilePath}"
    )
}