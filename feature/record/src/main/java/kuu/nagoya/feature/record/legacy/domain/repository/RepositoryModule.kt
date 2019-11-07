package kuu.nagoya.feature.record.legacy.domain.repository

import org.koin.dsl.module

internal val repositoryModule = module {
    single { AudioRepositoryImpl() as AudioRepository }
}
