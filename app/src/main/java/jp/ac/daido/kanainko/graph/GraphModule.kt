package jp.ac.daido.kanainko.graph

import jp.ac.daido.kanainko.graph.view.viewModule
import jp.ac.daido.kanainko.record.domain.domainModule

val graphModuleList =
    domainModule + viewModule
