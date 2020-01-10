package kuu.nagoya.feature.result

import kotlinx.coroutines.CoroutineScope
import kuu.nagoya.data.tmprecorddata.TmpRecordDataReadonlyRepository
import kuu.nagoya.feature.result.usecase.PredictVoiceUsecase

internal class PredictWordLiveDataFactory(
    private val tmpRecordDataReadonlyRepository: TmpRecordDataReadonlyRepository,
    private val predictWordUsecase: PredictVoiceUsecase
) {
    fun create(
        coroutineScope: CoroutineScope
    ): PredictWordLiveData {
        return PredictWordLiveData(
            predictWordUsecase,
            tmpRecordDataReadonlyRepository,
            coroutineScope
        )
    }
}
