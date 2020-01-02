package kuu.nagoya.dashboard.viewentity

import kuu.nagoya.dashboard.entity.Record
import kuu.nagoya.dashboard.entity.toFilePath
import kuu.nagoya.dashboard.entity.toRecordName

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

internal fun RecordViewEntity.convertBack(): Record {
    return Record(
        this.id,
        this.audioFilePath.toFilePath(),
        this.title.toRecordName()
    )
}
