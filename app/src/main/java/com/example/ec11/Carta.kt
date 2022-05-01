package com.example.ec11


import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View

class Cartas: View {
    var num: Int? = null
    var simbolo: String? = null
    constructor(context: Context,attrs:AttributeSet?):super(context,attrs){



    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }



}