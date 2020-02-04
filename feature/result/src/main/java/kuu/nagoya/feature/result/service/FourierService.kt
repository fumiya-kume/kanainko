package kuu.nagoya.feature.result.service

import android.graphics.Bitmap

interface FourierService {
    suspend fun audioDataToImage(data: List<Short>): Bitmap
}