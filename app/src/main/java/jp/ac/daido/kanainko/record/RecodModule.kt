package jp.ac.daido.kanainko.record

import jp.ac.daido.kanainko.record.domain.AudioService
import jp.ac.daido.kanainko.record.domain.service.AudioServiceImpl
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val recordModule = module {
    factory { AudioServiceImpl() as AudioService }
    viewModel { (presenter: RecordPresenter) -> RecordViewModel(presenter, get()) }

}