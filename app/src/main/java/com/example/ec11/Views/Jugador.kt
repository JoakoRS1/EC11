package com.example.ec11.Views

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.ec11.MainActivity
import com.example.ec11.R

class Jugador: View {
    private val mPaint : Paint = Paint()
    private var mSize : Int = 0
    public var subMazo = mutableListOf<Carta>()
    var posi: Int =0
    var cant: Int =0

    constructor(context: Context, pos: Int, cantidad:Int) : super (context){
        posi = pos;
        cant = cantidad;
    }

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


}