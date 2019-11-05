package kuu.nagoya.dashboard.viewentity

import kuu.nagoya.entity.recordentity.Record

private fun kuu.nagoya.entity.recordentity.Record.convert(): RecordViewEntity {
    return RecordViewEntity(
        this.id,
        this.recordName.name,
        this.filePath.path
    )
}

internal fun List<kuu.nagoya.entity.recordentity.Record>.convert(): List<RecordViewEntity> {
    return this.map { it.convert() }
}