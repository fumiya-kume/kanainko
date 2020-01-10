package kuu.nagoya.feature.result

import kotlinx.coroutines.CoroutineScope
import kuu.nagoya.data.tmprecorddata.TmpRecordDataReadonlyRepository
import kuu.nagoya.feature.result.service.FourieService
import kuu.nagoya.feature.result.usecase.RecordDataToUserVoiceDataUsecase

internal class UserVoiceSpectrogramLiveDataFactory(
    private val fourieService: FourieService,
    private val tmpRecordDataReadonlyRepository: TmpRecordDataReadonlyRepository,
    private val recordDataToUserVoiceDataUsecase: RecordDataToUserVoiceDataUsecase
) {
    fun create(
        coroutineScope: CoroutineScope
    ): UserVoiceSpectrogramLiveData {
        return UserVoiceSpectrogramLiveData(
            fourieService,
            tmpRecordDataReadonlyRepository,
            recordDataToUserVoiceDataUsecase,
            coroutineScope
        )
    }
}