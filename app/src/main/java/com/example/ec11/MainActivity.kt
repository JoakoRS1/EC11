package com.example.ec11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.*
import java.util.*

class MainActivity : AppCompatActivity() {

    class Carta (val numero: Int, val palo: String){}
    public var Mazo = mutableListOf<Carta>()






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        for (i in arrayOf("cora","espada","trébol","cocos")){
            for (j in 1..13) {
                Mazo.add(Carta(j,i))
            }
        }
        shuffle(Mazo,52)
    }

    fun shuffle(cards: MutableList<Carta>, n: Int) {
        var card = cards
        val rand = Random()
        for (i in 0 until n) {
            val r: Int = i + rand.nextInt(52 - i)
            //swapping the elements
            val temp: Carta = card[r]
            card[r] = card[i]
            card[i] = temp
        }
    }
}