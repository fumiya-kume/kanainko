package jp.ac.daido.kanainko.graph.domain.usecase

import jp.ac.daido.kanainko.record.domain.usecase.FourierTransformUsecase
import jp.ac.daido.kanainko.record.domain.usecase.FourierTransformUsecaseImpl
import jp.ac.daido.kanainko.record.domain.usecase.LoadSoundRawDataUsecase
import jp.ac.daido.kanainko.record.domain.usecase.LoadSoundRawDataUsecaseImpl
import jp.ac.daido.kanainko.record.domain.usecase.LoadSoundVolumeUsecase
import jp.ac.daido.kanainko.record.domain.usecase.LoadSoundVolumeUsecaseImpl
import jp.ac.daido.kanainko.record.domain.usecase.StartAudioRecordingUsecase
import jp.ac.daido.kanainko.record.domain.usecase.StartAudioRecordingUsecaseImpl
import org.koin.dsl.module

internal val usecaseModule = module {
    factory { LoadSoundRawDataUsecaseImpl(get()) as LoadSoundRawDataUsecase }
    factory { LoadSoundVolumeUsecaseImpl(get()) as LoadSoundVolumeUsecase }
    factory { StartAudioRecordingUsecaseImpl(get()) as StartAudioRecordingUsecase }
    factory { FourierTransformUsecaseImpl() as FourierTransformUsecase }
}
