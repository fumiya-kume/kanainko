package kuu.nagoya.feature.record.view

import kuu.nagoya.feature.record.usecase.CreateTmpRecordUsecase
import kuu.nagoya.feature.record.usecase.RestartTmpRecordUsecase
import kuu.nagoya.feature.record.usecase.SaveTmpRecordUsecase
import kuu.nagoya.feature.record.usecase.impl.CreateTmpRecord
import kuu.nagoya.feature.record.usecase.impl.CreateTmpRecordImpl
import kuu.nagoya.feature.record.usecase.impl.CreateTmpRecordUsecaseImpl
import kuu.nagoya.feature.record.usecase.impl.RestartTmpRecordUsecaseImpl
import kuu.nagoya.feature.record.usecase.impl.SaveTmpRecordUsecaseImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    factory { CreateTmpRecordUsecaseImpl(get()) as CreateTmpRecordUsecase }
    factory { RestartTmpRecordUsecaseImpl() as RestartTmpRecordUsecase }
    factory { SaveTmpRecordUsecaseImpl() as SaveTmpRecordUsecase }
    factory { CreateTmpRecordImpl() as CreateTmpRecord }

    factory { TmpRecordLiveDataFactory(get(), get(), get()) }
    viewModel { RecordViewModel(get()) }
}
