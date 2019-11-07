package kuu.nagoya.feature.record.legacy.domain

import kuu.nagoya.feature.record.legacy.domain.repository.repositoryModule
import kuu.nagoya.feature.record.legacy.domain.usecase.usecaseModule

val domainModule = repositoryModule + usecaseModule
