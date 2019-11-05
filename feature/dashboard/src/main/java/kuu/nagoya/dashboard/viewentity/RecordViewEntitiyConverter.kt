package kuu.nagoya.dashboard.viewentity

import kuu.nagoya.dashboard.entity.Record

private fun Record.convert(): RecordViewEntity {
    return RecordViewEntity(
        this.id,
        this.recordName.name,
        this.filePath.path
    )
}

internal fun List<Record>.convert(): List<RecordViewEntity> {
    return this.map { it.convert() }
}