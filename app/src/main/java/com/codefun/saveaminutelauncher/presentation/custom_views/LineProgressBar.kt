package com.codefun.saveaminutelauncher.presentation.custom_views

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View

/**
 * Created by Agam on 24,June,2022
 */

class LineProgressBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var spaceForEachPercentage = 0f
    private var percentage = 50

    private var paintActive = Paint().apply {
        color = getColor(com.google.android.material.R.attr.colorPrimaryVariant)
        strokeCap = Paint.Cap.ROUND
        strokeWidth = 1f
    }

    private var paintInActive = Paint().apply {
        color = getColor(com.google.android.material.R.attr.colorOnPrimary)
        strokeCap = Paint.Cap.ROUND
        strokeWidth = 1f
        alpha = 50
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        spaceForEachPercentage = w / 101f
    }

    override fun onDraw(canvas: Canvas?) {
        for (i in 0..100 step 1) {
            if (i <= percentage)
                canvas?.drawLine(
                    (i * spaceForEachPercentage),
                    0f,
                    (i * spaceForEachPercentage),
                    height.toFloat(),
                    paintActive
                )
            else
                canvas?.drawLine(
                    (i * spaceForEachPercentage),
                    0f,
                    (i * spaceForEachPercentage),
                    height.toFloat(),
                    paintInActive
                )
        }
        super.onDraw(canvas)
    }

    private fun getColor(colorAttr: Int): Int {
        val typedValue = TypedValue()
        val a: TypedArray = context.obtainStyledAttributes(typedValue.data, intArrayOf(colorAttr))
        val color = a.getColor(0, 0)
        a.recycle()
        return color
    }

    fun setProgress(percentage: Int) {
        this.percentage = percentage
        invalidate()
    }
}