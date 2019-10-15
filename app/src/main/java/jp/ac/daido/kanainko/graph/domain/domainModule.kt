package jp.ac.daido.kanainko.graph.domain

import jp.ac.daido.kanainko.graph.domain.repository.repositoryModule
import jp.ac.daido.kanainko.graph.domain.usecase.usecaseModule
import org.koin.dsl.module

val domainModule = module {
    repositoryModule
    usecaseModule
}