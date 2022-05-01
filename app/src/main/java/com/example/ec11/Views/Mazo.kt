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
        var lastcard=0f
        var cardWidth=mWidth/8

        for (i in 1..cantidad){
            var rect= RectF(lastcard,mHeight*0.1f, lastcard+cardWidth,mHeight*0.3f)
            lastcard=lastcard+cardWidth
            canvas!!.drawRect(rect,mPaint)

        }
    }

}