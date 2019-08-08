package jp.ac.daido.kanainko.record

import jp.ac.daido.kanainko.record.domain.RecordFileNameRepository
import jp.ac.daido.kanainko.record.domain.service.AudioRecordService
import jp.ac.daido.kanainko.record.domain.service.AudioRecordServiceImpl
import jp.ac.daido.kanainko.record.infra.RecordFileNameRepositoryImpl
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val recordModule = module {
    factory { RecordFileNameRepositoryImpl() as RecordFileNameRepository }
    viewModel { (presenter: RecordPresenter) -> RecordViewModel(presenter, get(), get()) }
    single { AudioRecordServiceImpl() as AudioRecordService }

}