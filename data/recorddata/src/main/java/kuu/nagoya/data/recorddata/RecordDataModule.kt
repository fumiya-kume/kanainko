package kuu.nagoya.data.recorddata

import kuu.nagoya.data.recorddata.impl.RecordDataRepositoryImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val recordDataModule = module {
    factory { RecordDataRepositoryImpl(androidApplication()) as RecordDataRepository }
}