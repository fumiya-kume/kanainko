package kuu.nagoya.dashboard

import kuu.nagoya.dashboard.infra.RecordListRepositoryImpl
import kuu.nagoya.dashboard.usecase.RecordListReadonlyRepository
import kuu.nagoya.dashboard.usecase.RecordListRepository
import kuu.nagoya.dashboard.usecase.RemoveAllRecordListUsecase
import kuu.nagoya.dashboard.usecase.ResetRecordListUsecase
import kuu.nagoya.dashboard.usecase.impl.RemoveAllRecordListUsecaseImpl
import kuu.nagoya.dashboard.usecase.impl.ResetRecordListUsecaseImpl
import kuu.nagoya.dashboard.view.DashboardViewModel
import kuu.nagoya.dashboard.view.RecordListLiveDataFactory
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dashBoardModule = module {
    factory { RecordListLiveDataFactory(get(), get(), get()) }
    factory { RecordListRepositoryImpl(androidApplication()) as RecordListReadonlyRepository }
    factory { RecordListRepositoryImpl(androidApplication()) as RecordListRepository }
    factory { ResetRecordListUsecaseImpl(get()) as ResetRecordListUsecase }
    factory { RemoveAllRecordListUsecaseImpl(get()) as RemoveAllRecordListUsecase }
    viewModel { DashboardViewModel(get()) }
}