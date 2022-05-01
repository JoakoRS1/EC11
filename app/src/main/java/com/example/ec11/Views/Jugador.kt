package com.example.ec11.Views

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.ec11.R

class Jugador: View {
    private val mPaint : Paint = Paint()
    private var mSize : Int = 0
    var SubMazo: ArrayList<Carta> = ArrayList<Carta>();

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs){
        val a: TypedArray = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.Jugador,
            0,
            0
        )
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = View.MeasureSpec.getSize(widthMeasureSpec);
        val height = View.MeasureSpec.getSize(heightMeasureSpec);
        mSize = Math.min(width, height)

        setMeasuredDimension(mSize, mSize)
    }


    override fun onDraw(canvas: Canvas?){
        super.onDraw(canvas)
        numJugador(canvas!!)
    }

    private fun numJugador(canvas: Canvas){

        mPaint.color= Color.RED
        mPaint.textSize= mSize/11f
        //mPaint.textAlign= Paint.Align.LEFT

        canvas.drawText("1",mSize / 20f, 5 * mSize / 8f,mPaint)

    }



}