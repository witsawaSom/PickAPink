package com.example.myproject.ui

import android.annotation.TargetApi
import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.View


class GraphicsView : View {
    private val mArc: Path = Path()
    private var mPaintText: Paint
    private var hOffset = 0F
    private var c = 0F
    private var circleEdge = 2350F

    @JvmOverloads
    constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0)
            : super(context, attrs, defStyleAttr)

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes)

    override fun onDraw(canvas: Canvas) {
//        hOffset += 0.25F
        c += 1F
        hOffset = (c * (circleEdge / 360))

        if(c>=360F){
            c = 0F
            MY_TEXT = "Draw Text on Curve"
        }
        Log.d("TAG",hOffset.toString())
        if(c>=340F){
//            MY_TEXT = MY_TEXT.substring(0, MY_TEXT.length-1)
        }
        canvas.drawTextOnPath(MY_TEXT, mArc, hOffset, 0F, mPaintText)
        invalidate()
    }

    companion object {
        private var MY_TEXT = "Draw Text on Curve"
    }

    init {
        val oval = RectF((measuredWidth/2).toFloat()+50F, (measuredHeight/2).toFloat()+50F, 800F, 800F)
        mArc.addArc(oval, -180F, 360F)
        mPaintText = Paint(Paint.ANTI_ALIAS_FLAG)
        mPaintText.style = Paint.Style.FILL_AND_STROKE
        mPaintText.color = Color.WHITE
        mPaintText.textSize = 60f
    }

}