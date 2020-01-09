package kuu.nagoya.feature.result.service

import android.content.res.AssetFileDescriptor

interface AssetsService {
    fun assetFileDescriptor(filePath: String): AssetFileDescriptor
}