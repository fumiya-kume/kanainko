package jp.ac.daido.kanainko.graph.view

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    factory { SoundRawDataLiveDataFactory(get()) }
    factory { SoundVolumeLiveDataFactory(get()) }
    viewModel { GraphViewModel(get(), get()) }
}