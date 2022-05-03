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

    //var adapter = CustomAdapter(mutableListOf<Carta>())

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
        tirarCarta(Mesa[pos-1].number, Mesa[pos-1].palo)
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

        for(j in 0..2) {
            for (i in 1..8){
                jugadores[j].subMazo.add(agregarCarta())
                jugadores[j].cant++
           }
       }
        Mesa.add(agregarCarta())
        Log.i("MESA",Mesa[pos].number.toString() + " "+Mesa[pos].palo)
        val cartaMesa = findViewById<LinearLayout> (R.id.cartaMesa)
        cartaMesa.addView(Mesa[pos])
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

    fun recycle(): RecyclerView{
        val recyclerView = findViewById<RecyclerView> (R.id.my_recycler_view)
        //val cartaMesa = findViewById<LinearLayout> (R.id.cartaMesa)

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView.layoutManager = layoutManager
        return recyclerView
    }


    fun dibujarCartas(){
        val adapter = CustomAdapter(jugadores[0].subMazo)//CAMBIARRR
        recycle().adapter = adapter
        tirarCarta(Mesa[pos-1].number, Mesa[pos-1].palo)
    }

    fun tirarCarta(numeroM: Int, paloM: String){

        val adapter = CustomAdapter(jugadores[aTurno[0]].subMazo)
        recycle().adapter = adapter

        adapter.setOnItemClickListener(object : CustomAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {

                var MazoJugador= jugadores[aTurno[0]].subMazo
                var cartaSelec = MazoJugador[position]
                /*Toast.makeText(
                    this@MainActivity,
                    "SELECCIONASTE LA CARTA"+
                            cartaSelec.number+
                            cartaSelec.palo, Toast.LENGTH_SHORT).show()
                Log.i("CartaSelec",cartaSelec?.number.toString() + " "+cartaSelec?.palo)*/

                var numSelec = cartaSelec.number
                var paloSelec = cartaSelec.palo
                //leer carta en mesa
                Toast.makeText(
                    this@MainActivity, "numero Sel: " + numSelec, Toast.LENGTH_SHORT).show()

                if (numSelec == numeroM || paloSelec == paloM){
                    Mesa.add(cartaSelec)
                    val cartaMesa = findViewById<LinearLayout> (R.id.cartaMesa)
                    cartaMesa.removeAllViews()
                    cartaMesa.addView(Mesa[pos])
                    pos++

                    jugadores[aTurno[0]].subMazo.remove(cartaSelec)
                    jugadores[aTurno[0]].cant--
                    imprimirTextos()
                    dibujarCartas()
                    Toast.makeText(
                        this@MainActivity, "carta igual a mesa", Toast.LENGTH_SHORT).show()

                    //agregar a la mesa

                    /*if (cartaSeleccion()!!.number== 11){
                        var aux = aTurno[0]
                        var aux2 = aTurno[1]
                        aTurno.removeFromStart(2)
                        aTurno.addLast(aux)
                        aTurno.addLast(aux2)
                    }*/
                    if(cartaSelec!!.number==13){
                        for (i in 0..2){
                            jugadores[aTurno[1]].subMazo.add(agregarCarta())
                            jugadores[aTurno[1]].cant++
                        }

                    }

                }else{
                    Toast.makeText(
                        this@MainActivity, ":(", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    fun agregarCarta (): Carta{
        //-1 Mazo principal
        var carta_repartida=Mazo.removeAt(0)
        CartasenMazo-=1
        return carta_repartida
    }

    fun PasarTurno(){
        //var areaJug = findViewById<LinearLayout>(R.id.areaJugadorTurno)
        val bPasar = findViewById<Button>(R.id.bPasar);
        bPasar.setOnClickListener{
            val a= findViewById<TextView>(R.id.JSigTotal)//PRUEBA
            /*Toast.makeText(
                this@MainActivity,
                "Se pasó turno", Toast.LENGTH_SHORT).show()*/

            var aux = aTurno[0]
            aTurno.removeFromStart(1)
            aTurno.addLast(aux)

            dibujarCartas()
            imprimirTextos()
        }
    }

    fun RobarCarta(){
        val bRobar = findViewById<Button>(R.id.bRobar);
        bRobar.setOnClickListener{
            val a= findViewById<TextView>(R.id.JSigTotal)//PRUEBA

            jugadores[aTurno[0]].subMazo.add(agregarCarta())
            jugadores[aTurno[0]].cant++

            Toast.makeText(
                this@MainActivity,
                "Se robó carta", Toast.LENGTH_SHORT).show()

            dibujarCartas()
            imprimirTextos()
        }
    }



    fun imprimirTextos(){
        val TvCartasenMazo= findViewById<TextView>(R.id.TVCartasEnMAzo)
        TvCartasenMazo.text=CartasenMazo.toString()

        val JActualId= findViewById<TextView>(R.id.JActualId)
        val JSiguienteId= findViewById<TextView>(R.id.JSigId)
        val JSiguienteSigId= findViewById<TextView>(R.id.JSigSigId)

        val JActual= findViewById<TextView>(R.id.JActualTotal)
        val JSiguiente= findViewById<TextView>(R.id.JSigSigTotal)
        val JSiguienteSig= findViewById<TextView>(R.id.JSigTotal)

        JActualId.text="Jugador " + jugadores[aTurno[0]].posi.toString()+ ":  "
        JSiguienteId.text="Jugador " + jugadores[aTurno[1]].posi.toString()+ ":  "
        JSiguienteSigId.text="Jugador " + jugadores[aTurno[2]].posi.toString()+ ":  "

        JActual.text= jugadores[aTurno[0]].cant.toString()
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
            if(jugadores[i].cant == 1){
                val num = i +1
                Toast.makeText(applicationContext,
                    "El jugador "+ num +" le queda una carta",Toast.LENGTH_SHORT).show()
            }
        }
    }

}