package com.example.ec11.Views

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.View
import com.example.ec11.R

class Turnos: View {

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        val a: TypedArray = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.Turnos,
            0,
            0
        )
    }

}