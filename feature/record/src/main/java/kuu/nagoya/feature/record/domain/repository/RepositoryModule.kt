package kuu.nagoya.feature.record.domain.repository

import org.koin.dsl.module

internal val repositoryModule = module {
    single { AudioRepositoryImpl() as AudioRepository }
}
