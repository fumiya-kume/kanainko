package kuu.nagoya.feature.result.service

import android.graphics.Bitmap
import android.graphics.Color
import edu.emory.mathcs.jtransforms.fft.DoubleFFT_1D
import kotlin.math.absoluteValue

internal class FourieServiceImpl : FourieService {
    override suspend fun audioDataToImage(data: List<Short>): Bitmap {
        val sliceSize = 2
        val windowSize = 160
        val maxSize = ((16000 - windowSize) / sliceSize) - 1
        val heightSize = windowSize

        var imageData =
            (0 until ((data.size - windowSize) / sliceSize))
                .map {
                    val audioData = data
                        .slice(IntRange(it * sliceSize, it * sliceSize + windowSize))
                        .map { it.toDouble() }

                    val fft = DoubleFFT_1D(windowSize)
                    fft.realForward(audioData.toDoubleArray())

                    audioData
                        .filterIndexed { index, _ -> index % 2 == 0 }
                        .map { 255 - (it.absoluteValue * 200) }
                        .map { it.toInt() }
                        .map { Color.argb(it, 0, 0, 0) }
                }
                .flatten()
                .toIntArray()

        if (imageData.size > maxSize) {
            imageData = imageData.take(maxSize).toIntArray()
        }

        return Bitmap.createBitmap(
            imageData,
            imageData.size / heightSize,
            heightSize / 2,
            Bitmap.Config.ARGB_8888
        )
    }
}