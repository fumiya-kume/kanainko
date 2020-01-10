package com.prevent.voice_data

import com.prevent.voice_data.domain.AssetsInitUsecase
import com.prevent.voice_data.domain.VoiceDataRepository
import com.prevent.voice_data.infra.AssetsInitUsecaseImpl
import com.prevent.voice_data.infra.VoiceDataRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val voiceDataModule = module {
    factory { AssetsInitUsecaseImpl(androidContext()) as AssetsInitUsecase }
    factory { VoiceDataRepositoryImpl(androidContext()) as VoiceDataRepository }
}