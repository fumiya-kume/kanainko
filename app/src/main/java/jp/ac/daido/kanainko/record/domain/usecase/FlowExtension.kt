package jp.ac.daido.kanainko.record.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.withIndex

internal suspend fun <T, V> Flow<T>.mapIndexed(expression: ((index: Int, value: T) -> V)): Flow<V> {
    return this.withIndex().map { expression(it.index, it.value) }
}
