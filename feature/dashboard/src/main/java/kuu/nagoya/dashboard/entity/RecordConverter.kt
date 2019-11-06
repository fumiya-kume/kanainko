package kuu.nagoya.dashboard.entity

import kuu.nagoya.data.recorddata.model.RecordDataModel

fun RecordDataModel.convert(): Record {
    return Record(
        this.id,
        this.filePath.toFilePath(),
        this.name.toRecordName()
    )
}

fun List<RecordDataModel>.convert(): List<Record> {
    return this.map { it.convert() }
}

fun Record.convertBack(): RecordDataModel {
    return RecordDataModel(
        this.id,
        this.recordName.name,
        this.filePath.path
    )
}

fun List<Record>.convertBack(): List<RecordDataModel> {
    return this.map { it.convertBack() }
}