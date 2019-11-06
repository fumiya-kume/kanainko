package kuu.nagoya.feature.record

import kuu.nagoya.feature.record.domain.domainModule
import kuu.nagoya.feature.record.view.viewModule

val recordModule = domainModule + viewModule
