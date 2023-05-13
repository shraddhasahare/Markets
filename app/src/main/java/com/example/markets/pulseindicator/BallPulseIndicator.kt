package com.example.markets.pulseindicator

import android.animation.ValueAnimator
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class BallPulseIndicator : Indicator() {
    private val scaleFloats = floatArrayOf(
        SCALE,
        SCALE,
        SCALE
    )

    override fun draw(canvas: Canvas?, paint: Paint?) {
        val circleSpacing = 25F
        val radius = (Math.min(width, height) - circleSpacing * 2) / 6
        val x = width / 2 - (radius * 2 + circleSpacing)
        val y = (height / 2).toFloat()

        val colorArrayList: ArrayList<Int> = ArrayList()
        colorArrayList.add(Color.parseColor("#E9BE23"))
        colorArrayList.add(Color.parseColor("#4CBAFB"))
        colorArrayList.add(Color.parseColor("#FC4C60"))

        for (i in 0..2) {
            canvas?.save()
            val translateX = x + radius * 2 * i + circleSpacing * i
            canvas?.translate(translateX, y)
            canvas?.scale(scaleFloats[i], scaleFloats[i])
            paint?.color = colorArrayList[i]
            if (paint != null) {
                canvas?.drawCircle(0f, 0f, radius, paint)
            }
            canvas?.restore()
        }
    }

    override fun onCreateAnimators(): ArrayList<ValueAnimator> {
        val animators = ArrayList<ValueAnimator>()
        val delays = intArrayOf(150, 300, 450)
        for (i in 0..2) {
            val scaleAnim = ValueAnimator.ofFloat(1f, 0.3f, 1f)
            scaleAnim.duration = 700
            scaleAnim.repeatCount = -1
            scaleAnim.startDelay = delays[i].toLong()
            addUpdateListener(scaleAnim, ValueAnimator.AnimatorUpdateListener { animation ->
                scaleFloats[i] = animation.animatedValue as Float
                postInvalidate()
            })
            animators.add(scaleAnim)
        }
        return animators
    }

    companion object {
        const val SCALE = 1.0f
    }
}