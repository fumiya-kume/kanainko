package kuu.nagoya.data.tmprecorddata

import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val tmpRecordDataModule = module {
    factory { TmpRecordDataRepositoryImpl(androidApplication()) as TmpRecordDataReadonlyRepository }
    factory { TmpRecordDataRepositoryImpl(androidApplication()) as TmpRecordDataRepository }
}