package kuu.nagoya.feature.result.infra

import android.content.Context
import android.content.res.AssetFileDescriptor
import kuu.nagoya.data.tmprecorddata.entity.TmpRecordDataEntity
import kuu.nagoya.feature.result.domain.ModelVoiceRepository

class ModelVoiceRepositoryImpl(
    private val context: Context
) : ModelVoiceRepository {
    override fun loadModelVoice(tmpRecordDataEntity: TmpRecordDataEntity): AssetFileDescriptor {
        return context.resources.assets.openFd("")
    }
}