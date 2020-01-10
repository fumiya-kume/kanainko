package kuu.nagoya.feature.result.domain

import android.content.res.AssetFileDescriptor
import kuu.nagoya.data.tmprecorddata.entity.TmpRecordDataEntity

interface ModelVoiceRepository {
    fun loadModelVoice(tmpRecordDataEntity: TmpRecordDataEntity): AssetFileDescriptor
}