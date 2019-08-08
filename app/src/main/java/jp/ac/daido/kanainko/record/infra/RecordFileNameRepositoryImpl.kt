package jp.ac.daido.kanainko.record.infra

import jp.ac.daido.kanainko.record.domain.RecordFileNameRepository
import jp.ac.daido.kanainko.record.domain.entity.FilePathEntity
import java.util.*

internal class RecordFileNameRepositoryImpl : RecordFileNameRepository {
    override suspend fun generateRecordFileName(): FilePathEntity {
        val dateString = Date(System.currentTimeMillis()).time.toString()
        return FilePathEntity(dateString)
    }
}