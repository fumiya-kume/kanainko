package jp.ac.daido.kanainko.record.view

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    viewModel { RecordViewModel() }
}