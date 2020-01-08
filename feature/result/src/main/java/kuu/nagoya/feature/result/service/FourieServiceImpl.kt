package kuu.nagoya.feature.result.service

import android.graphics.Bitmap
import edu.emory.mathcs.jtransforms.fft.DoubleFFT_1D

internal class FourieServiceImpl : FourieService {
    override suspend fun audioDataToImage(data: List<Short>): Bitmap {
        val heightSize = 500
        val width = data.size / heightSize
        val height: Int = heightSize / 2
        val imageData = data
            .chunked(heightSize)
            .map { it.map { it.toDouble() } }
            .map {
                val fft = DoubleFFT_1D(it.size)
                fft.realForward(it.toDoubleArray())
                data
                    .filterIndexed { index, _ -> index % 2 == 0 }
                    .map { 255 - (it * 255) }
            }
            .flatten()
            .toIntArray()
        return Bitmap.createBitmap(imageData, width, height, Bitmap.Config.ARGB_8888)
    }
}