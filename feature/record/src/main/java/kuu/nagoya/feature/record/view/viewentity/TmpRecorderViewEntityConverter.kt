package kuu.nagoya.feature.record.view.viewentity

import kuu.nagoya.feature.record.entity.TmpRecord

internal fun TmpRecord.convert(): TmpRecorderViewEntity {
    return TmpRecorderViewEntity(
        this.id,
        this.recordName.name
    )
}