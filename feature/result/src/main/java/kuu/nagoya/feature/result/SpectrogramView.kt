package kuu.nagoya.feature.result

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.ImageView


class SpectrogramView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ImageView(context, attrs, defStyleAttr) {

    private val paint: Paint = Paint().apply {
        this.strokeWidth = 1F
    }
    private var bmp: Bitmap? = null
    private var data: List<List<Double>> = listOf()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (data.isEmpty()) {
            return
        }
        val width = data.size
        val height: Int = data[0].size
        val arrayCol = IntArray(width * height)
        var counter = 0
        (0 until height).forEach { i ->
            (0 until width).forEach { j ->
                if (data.size > j && data.elementAt(j).size > i) {
                    val value = 255 - (data.elementAt(j).elementAt(i) * 255).toInt()
                    val color = value shl 16 or (value shl 8) or value or (255 shl 24)
                    arrayCol[counter] = color
                    counter++
                }

            }
        }
        bmp = Bitmap.createBitmap(arrayCol, width, height, Bitmap.Config.ARGB_8888)

    }

    fun setData(data: List<List<Double>>) {
        this.data = data

        this.invalidate()
    }
}