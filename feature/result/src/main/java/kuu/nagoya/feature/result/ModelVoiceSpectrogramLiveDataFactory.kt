package kuu.nagoya.feature.result

import kotlinx.coroutines.CoroutineScope
import kuu.nagoya.data.tmprecorddata.TmpRecordDataReadonlyRepository
import kuu.nagoya.feature.result.service.FourieService
import kuu.nagoya.feature.result.usecase.RecordDataToModelVoiceDataUsecase

internal class ModelVoiceSpectrogramLiveDataFactory(
    private val tmpRecordDataReadonlyRepository: TmpRecordDataReadonlyRepository,
    private val fourieService: FourieService,
    private val recordDataToModelVoiceDataUsecase: RecordDataToModelVoiceDataUsecase
) {
    fun create(
        coroutineScope: CoroutineScope
    ): ModelVoiceSpectrogramLiveData {
        return ModelVoiceSpectrogramLiveData(
            tmpRecordDataReadonlyRepository,
            fourieService,
            recordDataToModelVoiceDataUsecase,
            coroutineScope
        )
    }
}