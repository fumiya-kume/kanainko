package kuu.nagoya.feature.result.service

import android.graphics.Bitmap

interface FourieService {
    suspend fun audioDataToImage(data: List<Short>): Bitmap
}