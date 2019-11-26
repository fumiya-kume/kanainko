package kuu.nagoya.feature.record.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kuu.nagoya.feature.record.view.recorder.Recorder

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

        // 音量をシュッと取り出す
        val audioPower = (recorder?.getMaxAmplitude() ?: 0.0)
        // 全体の音量の比率を取り出す
        // 0.0 - 1.0
        val percentOfAudioPower = audioPower / 100
        // 左上は0なので上からの長さに変換する
        val barPosition = height - (height * percentOfAudioPower)

        canvas?.drawRect(
            0F,
            barPosition.toFloat(),
            width.toFloat(),
            height.toFloat(),
            paint
        )

        postInvalidateDelayed(100)
    }
}