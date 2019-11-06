package kuu.nagoya.feature.record.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kuu.nagoya.feature.record.domain.recorder.Recorder

internal class AudioVolumeGraph @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var recorder: Recorder? = null

    fun setMediaRecorder(recorder: Recorder) {
        this.recorder = recorder
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {

        val paint = Paint().apply {
            isAntiAlias = true
            color = Color.RED
            strokeWidth = 10F
        }

        val barPosition =
            (height.toFloat() - (recorder?.maxAmplitude() ?: 0 * height).toFloat()) - 100

        canvas?.let {
            it.drawRect(
                0F,
                barPosition,
                width.toFloat(),
                height.toFloat(),
                paint
            )
        }

        postInvalidateDelayed(70)
    }
}