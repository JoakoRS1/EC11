package com.example.ec11.Views

import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.ec11.R

class Carta: View {
    private val mPaint: Paint = Paint()
    private var mSize : Float = 0f
    private var mWidth : Float = 0f
    private var mHeight : Float = 0f
    var number: String= "12"
    var palo: String= "espada"



    constructor(context: Context, attrs: AttributeSet):super(context,attrs){

        val a : TypedArray = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.Carta,
            0,
            0)

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
        mPaint.color= Color.WHITE
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

        var symbol: Bitmap?= null

        var rect = RectF(mWidth * 0.2f, mHeight * 0.3f, mWidth * 0.8f, mHeight * 0.65f)
        if (palo=="corazon") {
            symbol= BitmapFactory.decodeResource(resources, R.drawable.heart_cards)

        }
        else if (palo=="espada") {
            symbol= BitmapFactory.decodeResource(resources, R.drawable.spades_cards)

        }
        else if (palo=="trebol") {
            symbol= BitmapFactory.decodeResource(resources, R.drawable.trebol_cards)

        }
        else{
            symbol= BitmapFactory.decodeResource(resources, R.drawable.diamond_cards)
        }
        canvas?.drawBitmap(symbol!!,null,rect, Paint())
    }


}