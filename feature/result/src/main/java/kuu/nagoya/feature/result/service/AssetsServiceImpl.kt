package kuu.nagoya.feature.result.service

import android.content.Context
import android.content.res.AssetFileDescriptor

internal class AssetsServiceImpl(
    private val context: Context
) : AssetsService {
    override fun assetFileDescriptor(filePath: String): AssetFileDescriptor {
        return context.resources.assets.openFd(filePath)
    }
}