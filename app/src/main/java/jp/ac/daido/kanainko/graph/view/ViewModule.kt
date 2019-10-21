package jp.ac.daido.kanainko.graph.view

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModule = listOf(
    module {
        factory { SoundRawDataLiveDataFactory(get()) }
        factory { SoundVolumeLiveDataFactory(get()) }
        factory { FourierSoundDataLiveDataFactory(get(), get()) }
        viewModel { GraphViewModel(get(), get(), get(), get()) }
    }
)
