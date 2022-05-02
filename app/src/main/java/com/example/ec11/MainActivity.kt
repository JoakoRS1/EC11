package com.example.ec11

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.collection.CircularIntArray
import androidx.core.view.drawToBitmap
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ec11.Views.*
import java.util.*
import java.util.concurrent.CancellationException


class MainActivity : AppCompatActivity() {
    //var Carta: Carta? = null
    var jugadores = mutableListOf<Jugador>()
    var aTurno = CircularIntArray()


    private var Mazo = mutableListOf<Carta>()
    private  var Mesa = mutableListOf<Carta>()
    private var pos = 0
    private var CartasenMazo: Int=52


    fun InicializaJuego(){
        var jugador1 = Jugador(this,1,0)
        var jugador2 = Jugador(this,2,0)
        var jugador3 = Jugador(this,3,0)


        jugadores.add(jugador1)
        jugadores.add(jugador2)
        jugadores.add(jugador3)

        aTurno.addLast(0)
        aTurno.addLast(1)
        aTurno.addLast(2)

        repartirInicial()
        dibujarCartas()

    }

    fun DuranteJuego(){

        imprimirTextos()
        PasarTurno()
        RobarCarta()
        unaCarta()

    }

    fun repartirInicial(){
        for (i in arrayOf("corazon","espada","trebol","diamante")){
            for (j in 1..13) {
                Mazo.add(Carta(this,j,i))
            }
        }
        shuffle(Mazo,52)

        for (i in 1..8){
            for(j in 0..2) {
                jugadores[j].subMazo.add(agregarCarta())
                jugadores[j].cant++
            }
        }
        Mesa.add(agregarCarta())
        Log.i("MESA",Mesa[pos].number.toString() + " "+Mesa[pos].palo)
        pos++

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

    fun dibujarCartas(){
        val recyclerView = findViewById<RecyclerView> (R.id.my_recycler_view)
        val cartaMesa = findViewById<LinearLayout> (R.id.cartaMesa)

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView.layoutManager = layoutManager



        val adapter = CustomAdapter(jugadores[0].subMazo)//CAMBIARRR
        recyclerView.adapter = adapter


        adapter.setOnItemClickListener(object : CustomAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {

                var MazoJugador= jugadores[0].subMazo //Referencia a este mazo (J1)
                Toast.makeText(
                    this@MainActivity,
                    "SELECCIONASTE LA CARTA"+
                            MazoJugador[position].number+
                            MazoJugador[position].palo, Toast.LENGTH_SHORT).show()
            }
        })
    }


    fun tirarCarta(cartaA: Carta,cartaM: Carta){

        //leer carta en mesa

        var cartaPos = findViewById<RecyclerView>(R.id.my_recycler_view)


        if (cartaM.number == cartaA.number){
            jugadores[aTurno[0]].subMazo.remove(cartaA)
            //agregar a la mesa

            if (cartaA.number== 11){
                var aux = aTurno[0]
                var aux2 = aTurno[1]
                aTurno.removeFromStart(2)
                aTurno.addLast(aux)
                aTurno.addLast(aux2)
            }
        }

    }





    /*fun crearVistaCarta(CardView: Carta){
        CardView.number= Mazo[3].number
        CardView.palo=Mazo[3].palo
    }*/

    fun agregarCarta (): Carta{
        //-1 Mazo principal
        var carta_repartida=Mazo.removeAt(0)
        CartasenMazo-=1
        return carta_repartida
    }

    fun PasarTurno(){
        var areaJug = findViewById<LinearLayout>(R.id.areaJugadorTurno)
        val bPasar = findViewById<Button>(R.id.bPasar);
        bPasar.setOnClickListener{
            val a= findViewById<TextView>(R.id.JSigTotal)//PRUEBA
            areaJug.removeAllViews()
            a.text="PASAAA"//PRUEBA


            var aux = aTurno[0]
            aTurno.removeFromStart(1)
            aTurno.addLast(aux)

            imprimirTextos()
        }
    }

    fun RobarCarta(){
        val bRobar = findViewById<Button>(R.id.bRobar);
        bRobar.setOnClickListener{
            val a= findViewById<TextView>(R.id.JSigTotal)//PRUEBA

            jugadores[aTurno[0]].subMazo.add(agregarCarta())
            jugadores[aTurno[0]].cant++

            imprimirTextos()
        }
    }



    fun imprimirTextos(){
        val TvCartasenMazo= findViewById<TextView>(R.id.TVCartasEnMAzo)
        TvCartasenMazo.text=CartasenMazo.toString()

        val JActual= findViewById<TextView>(R.id.JActualTotal)
        val JSiguiente= findViewById<TextView>(R.id.JSigSigTotal)
        val JSiguienteSig= findViewById<TextView>(R.id.JSigTotal)

        JActual.text=jugadores[aTurno[0]].cant.toString()
        JSiguiente.text=jugadores[aTurno[1]].cant.toString()
        JSiguienteSig.text=jugadores[aTurno[2]].cant.toString()
    }





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        InicializaJuego()
        DuranteJuego()
    }
    fun unaCarta()  {
        for(i in 0..2){
            if(jugadores[i].cant++ == 1){
                val num = i +1
                Toast.makeText(applicationContext,"El jugador "+ num +" le queda una carta",Toast.LENGTH_SHORT).show()
            }
        }
    }

}