package kuu.nagoya.dashboard.usecase

import kuu.nagoya.dashboard.entity.Record

internal interface PlayAudioUsecase {
    suspend fun execute(record: Record)
}
