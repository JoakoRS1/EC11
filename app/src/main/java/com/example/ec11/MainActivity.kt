package com.example.ec11

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.TextView
import com.example.ec11.Views.Carta
import org.w3c.dom.Text
import java.util.*
import java.util.jar.Attributes

class MainActivity : AppCompatActivity() {

    class Carta_Clase (val numero: Int, val palo: String){}
    private var Mazo = mutableListOf<Carta_Clase>()
    private var SubMazo1 = mutableListOf<Carta_Clase>()
    private var SubMazo2 = mutableListOf<Carta_Clase>()
    private var SubMazo3 = mutableListOf<Carta_Clase>()
    private var CartasenMazo: Int=52
    private var J1CartasSubMazo: Int=0
    private var J2CartasSubMazo: Int=0
    private var J3CartasSubMazo: Int=0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val CardView= findViewById<Carta>(R.id.carta)
        val TvCartasenMazo= findViewById<TextView>(R.id.TVCartasEnMAzo)
        val TvJ1CartasMazo= findViewById<TextView>(R.id.J1Total)
        val TvJ2CartasMazo= findViewById<TextView>(R.id.J2Total)
        val TvJ3CartasMazo= findViewById<TextView>(R.id.J3Total)





        for (i in arrayOf("corazon","espada","trebol","diamante")){
            for (j in 1..13) {
                Mazo.add(Carta_Clase(j,i))
            }
        }
        shuffle(Mazo,52)
        crearVistaCarta(CardView)
        for (i in 1..8){
            SubMazo1.add(robarCarta())
            J1CartasSubMazo++
            SubMazo2.add(robarCarta())
            J2CartasSubMazo++
            SubMazo3.add(robarCarta())
            J3CartasSubMazo++
        }

        TvCartasenMazo.text=CartasenMazo.toString()
        TvJ1CartasMazo.text=J1CartasSubMazo.toString()
        TvJ2CartasMazo.text=J2CartasSubMazo.toString()
        TvJ3CartasMazo.text=J3CartasSubMazo.toString()
    }

    fun shuffle(cards: MutableList<Carta_Clase>, n: Int) {
        var card = cards
        val rand = Random()
        for (i in 0 until n) {
            val r: Int = i + rand.nextInt(52 - i)
            //swapping the elements
            val temp: Carta_Clase = card[r]
            card[r] = card[i]
            card[i] = temp
        }
    }
    fun crearVistaCarta(CardView: Carta){
        CardView.number=Mazo[3].numero.toString()
        CardView.palo=Mazo[3].palo
    }


    fun robarCarta (): Carta_Clase{
        //-1 Mazo principal
        var carta_repartida=Mazo.removeAt(0)
        CartasenMazo-=1
        return carta_repartida
    }

}