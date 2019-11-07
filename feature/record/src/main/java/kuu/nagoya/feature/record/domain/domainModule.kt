package kuu.nagoya.feature.record.domain

import kuu.nagoya.feature.record.domain.repository.repositoryModule
import kuu.nagoya.feature.record.domain.usecase.usecaseModule

val domainModule = repositoryModule + usecaseModule
