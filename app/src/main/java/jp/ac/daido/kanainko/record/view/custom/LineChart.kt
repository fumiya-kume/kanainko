package jp.ac.daido.kanainko.record.view.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import jp.ac.daido.kanainko.R

internal class LineChart @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var data: List<Float> = emptyList()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let {
            if (data.isEmpty()) {
                return
            }

            val maxValue = data.max()
            val minValue = data.min()
            if (maxValue == null || minValue == null) {
                return
            }
            val scale = maxValue / height
            val pointList = data.map { height - (it / scale) }
            invalidate()

            (0 until (pointList.size - 1)).forEach {
                val startY = pointList.elementAt(it)
                val stopY = pointList.elementAt(it + 1)

                val scaleY = width / (pointList.size + 2)
                val startX = (scaleY * (it)).toFloat()
                val stopX = (scaleY * (it + 1)).toFloat()
                val paint = Paint().apply {
                    this.color = context.getColor(R.color.white)
                }
                canvas.drawLine(startX, startY, stopX, stopY, paint)
            }
        }
    }

    fun setData(
        data: List<Float>
    ) {
        this.data = data
        invalidate()
    }
}