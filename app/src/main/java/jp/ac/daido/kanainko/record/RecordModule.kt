package jp.ac.daido.kanainko.record

import jp.ac.daido.kanainko.record.domain.domainModule
import jp.ac.daido.kanainko.record.view.viewModule

val recordModule = domainModule + viewModule
