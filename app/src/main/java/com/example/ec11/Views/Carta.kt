package com.example.ec11.Views

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Size
import android.view.View
import com.example.ec11.R

class Carta: View {
    private val mPaint: Paint = Paint()
    private var mSize : Float = 0f
    private var mWidth : Float = 0f
    private var mHeight : Float = 0f
    private var number: String= ""
    private var palo: String= ""
    private var sam: String= "sam"


    constructor(context: Context, attrs: AttributeSet):super(context,attrs){
        number = 4.toString()
        palo="corazon"
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        mWidth = View.MeasureSpec.getSize(widthMeasureSpec).toFloat()
        mHeight = View.MeasureSpec.getSize(heightMeasureSpec).toFloat()

        setMeasuredDimension(widthMeasureSpec,heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas?){
        super.onDraw(canvas)
        drawCarta(canvas!!)
        drawNumbers(canvas!!)
        drawSymbols(canvas!!)
    }

    private fun drawCarta(canvas: Canvas?){
        mPaint.color= Color.BLACK
        mPaint.style= Paint.Style.FILL_AND_STROKE
        var rect= RectF(mWidth*0.1f,mHeight*0.1f, mWidth*0.9f,mHeight*0.9f)
        canvas!!.drawRect(rect,mPaint)
    }
    private fun drawNumbers(canvas: Canvas?){
        mPaint.color= Color.RED
        mPaint.textSize= mWidth*0.2f
        mPaint.textAlign= Paint.Align.LEFT

        canvas!!.drawText(number,mWidth/5f,mHeight/4f,mPaint)
        var mPaint2=mPaint

        mPaint2.textAlign= Paint.Align.RIGHT

        canvas!!.drawText(number,mWidth-mWidth/5f,mHeight-mHeight/4f+mWidth*0.2f,mPaint2)
    }

    private fun drawSymbols(canvas: Canvas?){
        var foto=BitmapFactory.decodeResource(resources, R.drawable.ulima_futuro)
        var rect= RectF(mWidth*0.1f,mHeight*0.4f, mWidth*0.9f,mHeight*0.6f)
        canvas?.drawBitmap(foto!!,null,rect, Paint())
    }
}