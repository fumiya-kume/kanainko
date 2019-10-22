package jp.ac.daido.kanainko.graph.domain.repository

import jp.ac.daido.kanainko.record.domain.repository.AudioRepository
import jp.ac.daido.kanainko.record.domain.repository.AudioRepositoryImpl
import org.koin.dsl.module

internal val repositoryModule = module {
    single { AudioRepositoryImpl() as AudioRepository }
}
