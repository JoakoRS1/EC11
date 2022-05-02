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
    var number: Int= 0
    var palo: String= "espada"
    var anchoCarta:Float=0f

    constructor(context: Context, numero : Int, paloT : String) : super (context){
        number = numero;
        palo = paloT;
    }

    constructor(context: Context, attrs: AttributeSet):super(context,attrs){



    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        mWidth = View.MeasureSpec.getSize(widthMeasureSpec).toFloat()
        mHeight = View.MeasureSpec.getSize(heightMeasureSpec).toFloat()
        anchoCarta= mHeight*0.6f
        setMeasuredDimension(200,heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas?){
        super.onDraw(canvas)
        drawCarta(canvas!!)
        drawNumbers(canvas!!)
        drawSymbols(canvas!!)

    }

    public fun drawCarta(canvas: Canvas?){
        mPaint.color= Color.WHITE
        mPaint.style= Paint.Style.FILL_AND_STROKE


        var rect= RectF(mWidth*0.1f,mHeight*0.1f, mWidth*0.1f+anchoCarta,mHeight*0.9f)
        canvas!!.drawRect(rect,mPaint)
    }
    private fun drawNumbers(canvas: Canvas?){
        mPaint.color= Color.RED
        mPaint.textSize= anchoCarta*0.2f
        mPaint.textAlign= Paint.Align.LEFT

        canvas!!.drawText(number.toString(),mWidth*0.1f+anchoCarta*0.125f,mHeight*0.25f,mPaint)
        var mPaint2=mPaint

        mPaint2.textAlign= Paint.Align.RIGHT

        canvas!!.drawText(number.toString(),mWidth*0.1f+anchoCarta * 0.875f,mHeight*0.85f,mPaint2)
    }
    private fun drawSymbols(canvas: Canvas?){

        var symbol: Bitmap?= null

        var rect = RectF(mWidth * 0.1f, mHeight * 0.3f, mWidth * 0.1f+anchoCarta, mHeight * 0.65f)
        if (palo=="corazon") {
            symbol= BitmapFactory.decodeResource(resources, R.drawable.corazon)
        }
        else if (palo=="espada") {
            symbol= BitmapFactory.decodeResource(resources, R.drawable.espada)
        }
        else if (palo=="trebol") {
            symbol= BitmapFactory.decodeResource(resources, R.drawable.trebol)
        }
        else{
            symbol= BitmapFactory.decodeResource(resources, R.drawable.diamante)
        }
        canvas?.drawBitmap(symbol!!,null,rect, Paint())
    }


}