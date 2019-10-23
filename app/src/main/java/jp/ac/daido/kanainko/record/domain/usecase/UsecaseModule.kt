package jp.ac.daido.kanainko.record.domain.usecase

import org.koin.dsl.module

internal val usecaseModule = module {
    factory { LoadSoundRawDataUsecaseImpl(get()) as LoadSoundRawDataUsecase }
    factory { LoadSoundVolumeUsecaseImpl(get()) as LoadSoundVolumeUsecase }
    factory { FourierTransformUsecaseImpl() as FourierTransformUsecase }
}
