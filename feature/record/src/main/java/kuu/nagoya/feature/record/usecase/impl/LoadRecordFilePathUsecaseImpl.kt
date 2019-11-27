package kuu.nagoya.feature.record.usecase.impl

import android.content.Context
import android.icu.text.SimpleDateFormat
import kuu.nagoya.data.tmprecorddata.TmpRecordDataReadonlyRepository
import kuu.nagoya.data.tmprecorddata.TmpRecordDataRepository
import kuu.nagoya.feature.record.entity.RecordFilePath
import kuu.nagoya.feature.record.usecase.LoadRecordFilePathUsecase
import java.util.Date


internal class LoadRecordFilePathUsecaseImpl(
    private val context: Context,
    private val tmpRecordDataRepository: TmpRecordDataRepository,
    private val tmpRecordDataReadonlyRepository: TmpRecordDataReadonlyRepository
) : LoadRecordFilePathUsecase {
    override suspend fun execute(): RecordFilePath {
        val now = Date(System.currentTimeMillis())
        val nowString = SimpleDateFormat("yyyyMMddHHmmssSS").format(now)
        val filePath = "${context.externalMediaDirs.first()}/${nowString}.wav"
        tmpRecordDataRepository.storeTmpData(
            tmpRecordDataReadonlyRepository
                .loadTmpRecordData()
                .copy(audioFilePath = filePath)
        )
        return RecordFilePath(
            filePath
        )
    }
}