package jp.ac.daido.kanainko.graph.domain.repository

import org.koin.dsl.module

internal val repositoryModule = module {
    single { AudioRepositoryImpl() as AudioRepository }
}
