package kuu.nagoya.feature.record

import kuu.nagoya.feature.record.legacy.domain.domainModule
import kuu.nagoya.feature.record.view.viewModule

val recordModule = domainModule + viewModule
