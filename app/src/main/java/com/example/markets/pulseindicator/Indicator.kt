package com.example.markets.pulseindicator

import android.animation.ValueAnimator
import android.graphics.*
import android.graphics.drawable.Animatable
import android.graphics.drawable.Drawable

abstract class Indicator : Drawable(), Animatable {
    private val mUpdateListeners = HashMap<ValueAnimator, ValueAnimator.AnimatorUpdateListener>()
    private var mAnimators: ArrayList<ValueAnimator>? = null
    private var alpha = 255
    var drawBound = ZERO_BOUNDS_RECT
    private var mHasAnimators = false
    private val mPaint = Paint()
    var color: Int
        get() = mPaint.color
        set(color) {
            mPaint.color = color
        }

    override fun setAlpha(alpha: Int) {
        this.alpha = alpha
    }

    override fun getAlpha(): Int {
        return alpha
    }

    override fun getOpacity(): Int {
        return PixelFormat.OPAQUE
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {}
    override fun draw(canvas: Canvas) {
        draw(canvas, mPaint)
    }

    abstract fun draw(canvas: Canvas?, paint: Paint?)
    abstract fun onCreateAnimators(): ArrayList<ValueAnimator>?
    override fun start() {
        ensureAnimators()
        if (mAnimators == null) {
            return
        }

        // If the animators has not ended, do nothing.
        if (isStarted) {
            return
        }
        startAnimators()
        invalidateSelf()
    }

    private fun startAnimators() {
        for (i in mAnimators!!) {

            //when the animator restart , add the updateListener again because they
            // was removed by animator stop .
            val updateListener = mUpdateListeners[i]
            if (updateListener != null) {
                i.addUpdateListener(updateListener)
            }
            i.start()
        }
    }

    private fun stopAnimators() {
        if (mAnimators != null) {
            for (animator in mAnimators!!) {
                if (animator != null && animator.isStarted) {
                    animator.removeAllUpdateListeners()
                    animator.end()
                }
            }
        }
    }

    private fun ensureAnimators() {
        if (!mHasAnimators) {
            mAnimators = onCreateAnimators()
            mHasAnimators = true
        }
    }

    override fun stop() {
        stopAnimators()
    }

    private val isStarted: Boolean
        private get() {
            for (animator in mAnimators!!) {
                return animator.isStarted
            }
            return false
        }

    override fun isRunning(): Boolean {
        for (animator in mAnimators!!) {
            return animator.isRunning
        }
        return false
    }

    /**
     * Your should use this to add AnimatorUpdateListener when
     * create animator , otherwise , animator doesn't work when
     * the animation restart .
     * @param updateListener
     */
    fun addUpdateListener(
        animator: ValueAnimator,
        updateListener: ValueAnimator.AnimatorUpdateListener
    ) {
        mUpdateListeners[animator] = updateListener
    }

    override fun onBoundsChange(bounds: Rect) {
        super.onBoundsChange(bounds)
        setDrawBounds(bounds)
    }

    fun setDrawBounds(drawBounds: Rect) {
        setDrawBounds(drawBounds.left, drawBounds.top, drawBounds.right, drawBounds.bottom)
    }

    fun setDrawBounds(left: Int, top: Int, right: Int, bottom: Int) {
        drawBound = Rect(left, top, right, bottom)
    }

    fun postInvalidate() {
        invalidateSelf()
    }

    fun getDrawBounds(): Rect {
        return drawBound
    }

    val width: Int
        get() = drawBound.width()
    val height: Int
        get() = drawBound.height()

    fun centerX(): Int {
        return drawBound.centerX()
    }

    fun centerY(): Int {
        return drawBound.centerY()
    }

    fun exactCenterX(): Float {
        return drawBound.exactCenterX()
    }

    fun exactCenterY(): Float {
        return drawBound.exactCenterY()
    }

    companion object {
        private val ZERO_BOUNDS_RECT = Rect()
    }

    init {
        mPaint.color = Color.WHITE
        mPaint.style = Paint.Style.FILL
        mPaint.isAntiAlias = true
    }
}
