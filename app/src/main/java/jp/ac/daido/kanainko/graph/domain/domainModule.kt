package jp.ac.daido.kanainko.graph.domain

import jp.ac.daido.kanainko.record.domain.repository.repositoryModule
import jp.ac.daido.kanainko.record.domain.usecase.usecaseModule

val domainModule = listOf(
    repositoryModule,
    usecaseModule
)
