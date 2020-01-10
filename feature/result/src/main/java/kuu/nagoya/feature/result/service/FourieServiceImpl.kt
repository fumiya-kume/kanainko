package kuu.nagoya.feature.result.service

import android.graphics.Bitmap
import android.graphics.Color
import edu.emory.mathcs.jtransforms.fft.DoubleFFT_1D
import kotlin.math.absoluteValue

internal class FourieServiceImpl : FourieService {
    override suspend fun audioDataToImage(data: List<Short>): Bitmap {
        val sliceSize = 8
        val windowSize = 320
        val heightSize = windowSize
        val width = data.size / heightSize
        val height: Int = heightSize / 2

        val imageData =
            (0 until ((data.size - windowSize) / sliceSize))
                .map {
                    val audioData = data
                        .slice(IntRange(it * sliceSize, it * sliceSize + windowSize))
                        .map { it.toDouble() }

                    val fft = DoubleFFT_1D(windowSize)
                    fft.realForward(audioData.toDoubleArray())

                    audioData
                        .filterIndexed { index, _ -> index % 2 == 0 }
                        .map { 255 - it.absoluteValue }
                        .map { it.toInt() }
                        .map { Color.argb(it, 0, 0, 0) }
                }
                .flatten()
                .toIntArray()

        return Bitmap.createBitmap(imageData, width, height, Bitmap.Config.ARGB_8888)
    }
}