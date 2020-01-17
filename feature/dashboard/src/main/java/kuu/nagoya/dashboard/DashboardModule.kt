package kuu.nagoya.dashboard

import kuu.nagoya.dashboard.infra.RecordListRepositoryImpl
import kuu.nagoya.dashboard.usecase.PlayAudioUsecase
import kuu.nagoya.dashboard.usecase.RecordListReadonlyRepository
import kuu.nagoya.dashboard.usecase.RecordListRepository
import kuu.nagoya.dashboard.usecase.RemoveAllRecordListUsecase
import kuu.nagoya.dashboard.usecase.ResetRecordListUsecase
import kuu.nagoya.dashboard.usecase.impl.PlayAudioUsecaseImpl
import kuu.nagoya.dashboard.usecase.impl.RemoveAllRecordListUsecaseImpl
import kuu.nagoya.dashboard.usecase.impl.ResetRecordListUsecaseImpl
import kuu.nagoya.dashboard.view.DashboardViewModel
import kuu.nagoya.dashboard.view.RecordListLiveDataFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dashBoardModule = module {
    factory { RecordListLiveDataFactory(get(), get(), get()) }
    factory { RecordListRepositoryImpl(get()) as RecordListReadonlyRepository }
    factory { RecordListRepositoryImpl(get()) as RecordListRepository }
    factory { ResetRecordListUsecaseImpl(get()) as ResetRecordListUsecase }
    factory { PlayAudioUsecaseImpl(get()) as PlayAudioUsecase }
    factory { RemoveAllRecordListUsecaseImpl(get()) as RemoveAllRecordListUsecase }
    viewModel { DashboardViewModel(get(), get()) }
}
