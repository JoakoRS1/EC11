package com.example.ec11.Views

import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class Mazo: View {
    private val mPaint: Paint = Paint()
    private var Lista_Mazo = mutableListOf<Int>(1,2,3,4,5)
    private var cantidad: Int= 5
    private var mWidth: Float= 0f
    private var mHeight : Float =0f

    constructor(context: Context, attrs: AttributeSet):super(context,attrs){
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        mWidth = View.MeasureSpec.getSize(widthMeasureSpec).toFloat()
        mHeight = View.MeasureSpec.getSize(heightMeasureSpec).toFloat()

        setMeasuredDimension(widthMeasureSpec,heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas?){
        super.onDraw(canvas)
        ordenarSubmazo(canvas!!)
    }
    fun ordenarSubmazo(canvas: Canvas?){
        mPaint.color=Color.BLACK
        mPaint.style=Paint.Style.STROKE
        var lastcard=0f
        var cardWidth=mWidth/7
        var BetweenCards=-mWidth*0.05f

        var cardHeight=mHeight*0.15f

        for (i in 0..cantidad-1){
            var rect= RectF(
                lastcard+BetweenCards,
                BetweenCards,
                lastcard+cardWidth,
                cardHeight+BetweenCards)
            lastcard+=cardWidth
            canvas!!.drawRect(rect,mPaint)

        }
    }

}