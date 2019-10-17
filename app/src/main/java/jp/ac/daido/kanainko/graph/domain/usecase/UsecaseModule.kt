package jp.ac.daido.kanainko.graph.domain.usecase

import org.koin.dsl.module

internal val usecaseModule = module {
    factory { LoadSoundRawDataUsecaseImpl(get()) as LoadSoundRawDataUsecase }
    factory { LoadSoundVolumeUsecaseImpl(get()) as LoadSoundVolumeUsecase }
    factory { StartAudioRecordingUsecaseImpl(get()) as StartAudioRecordingUsecase }
}
