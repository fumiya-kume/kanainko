package jp.ac.daido.kanainko.record.domain

import jp.ac.daido.kanainko.record.domain.entity.FilePathEntity

internal interface RecordFileNameRepository {
    suspend fun generateRecordFileName(): FilePathEntity
}