package jp.ac.daido.kanainko.graph.domain.usecase

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.consumeAsFlow

internal suspend fun <T, V> Flow<T>.mapIndexed(expression: ((index: Int, value: T) -> V)): Flow<V> {
    val channel = Channel<V>()
    this.collectIndexed { index, value ->
        channel.send(
            expression(index, value)
        )
    }
    return channel.consumeAsFlow()
}