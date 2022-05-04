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
    private var lanza = 0
    private var robo = 0
    var contnum =0
    var numAux =0
    var contJugada = 0

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
                /*Toast.makeText(
                    this@MainActivity, "numero Sel: " + numSelec, Toast.LENGTH_SHORT).show()*/

                if ((numSelec == numeroM || paloSelec == paloM)&& contJugada==0){
                    unaCarta()
                    Mesa.add(cartaSelec)
                    val cartaMesa = findViewById<LinearLayout> (R.id.cartaMesa)
                    cartaMesa.removeAllViews()
                    cartaMesa.addView(Mesa[pos])
                    pos++
                    lanza++


                    Log.i("MESA",lanza.toString() )

                    jugadores[aTurno[0]].subMazo.remove(cartaSelec)
                    jugadores[aTurno[0]].cant--

                    imprimirTextos()
                    dibujarCartas()
                    /*Toast.makeText(
                        this@MainActivity, "carta igual a mesa", Toast.LENGTH_SHORT).show()*/

                    //agregar a la mesa

                    if (cartaSelec!!.number== 11){

                        var aux = aTurno[0]
                        var aux2 = aTurno[1]
                        aTurno.removeFromStart(2)
                        aTurno.addLast(aux)
                        aTurno.addLast(aux2)
                        lanza = 0
                        robo = 0
                        contJugada=0
                        contnum=0
                        //PasarTurno()
                        dibujarCartas()
                        imprimirTextos()
                    }
                    if(cartaSelec!!.number==13){
                        for (i in 0..2){
                            if(Mazo.isNotEmpty()){
                            jugadores[aTurno[1]].subMazo.add(agregarCarta())
                            jugadores[aTurno[1]].cant++}
                            else{
                                Toast.makeText(
                                    this@MainActivity,
                                    "No hay cartas", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }



                    contJugada++

                    PasarTurno()
                    dibujarCartas()
                    imprimirTextos()
                }

                else if(contJugada==1 && (paloSelec == paloM)){
                    Log.i("Jugada","If Bloqueo")
                    Toast.makeText(
                        this@MainActivity,
                        "Terminó tu turno", Toast.LENGTH_SHORT).show()

                }
                else if(contJugada==1 && (numSelec == numeroM )){
                    unaCarta()
                    Mesa.add(cartaSelec)
                    val cartaMesa = findViewById<LinearLayout> (R.id.cartaMesa)
                    cartaMesa.removeAllViews()
                    cartaMesa.addView(Mesa[pos])
                    pos++
                    lanza++


                    Log.i("MESA",lanza.toString() )

                    jugadores[aTurno[0]].subMazo.remove(cartaSelec)
                    jugadores[aTurno[0]].cant--

                    imprimirTextos()
                    dibujarCartas()
                    /*Toast.makeText(
                        this@MainActivity, "carta igual a mesa", Toast.LENGTH_SHORT).show()*/

                    //agregar a la mesa

                    if (cartaSelec!!.number== 11){

                        var aux = aTurno[0]
                        var aux2 = aTurno[1]
                        aTurno.removeFromStart(2)
                        aTurno.addLast(aux)
                        aTurno.addLast(aux2)
                        lanza = 0
                        //PasarTurno()
                        dibujarCartas()
                        imprimirTextos()

                    }
                    if(cartaSelec!!.number==13){
                        for (i in 0..2){
                            if(Mazo.isNotEmpty()){
                                jugadores[aTurno[1]].subMazo.add(agregarCarta())
                                jugadores[aTurno[1]].cant++}
                            else{
                                Toast.makeText(
                                    this@MainActivity,
                                    "No hay cartas", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
                else {
                    Toast.makeText(
                        this@MainActivity,
                        "La carta " + numSelec + " " +paloSelec + " no coincide", Toast.LENGTH_SHORT).show()
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
        val bPasar = findViewById<Button>(R.id.bPasar);
        if(lanza<1){bPasar.isEnabled = false}
        else{
            bPasar.isEnabled = true
            bPasar.setOnClickListener{

                val a= findViewById<TextView>(R.id.JSigTotal)//PRUEBA


                var aux = aTurno[0]
                aTurno.removeFromStart(1)
                aTurno.addLast(aux)
                lanza = 0
                robo = 0
                contJugada=0
                contnum=0
                bPasar.isEnabled = false
                dibujarCartas()
                RobarCarta()
                imprimirTextos()}
        }
    }

    fun RobarCarta(){
        val bRobar = findViewById<Button>(R.id.bRobar)
        bRobar.isEnabled = true
        if(robo<1 && contJugada==0){
            bRobar.setOnClickListener{
                val a= findViewById<TextView>(R.id.JSigTotal)//PRUEBA
                if(Mazo.isNotEmpty()){
                    jugadores[aTurno[0]].subMazo.add(agregarCarta())
                    jugadores[aTurno[0]].cant++
                    robo++
                    bRobar.isEnabled = false
                    Log.i("robo",robo.toString() )
                    Toast.makeText(
                        this@MainActivity,
                        "Se robó carta", Toast.LENGTH_SHORT).show()

                    dibujarCartas()
                    imprimirTextos()
                    lanza++
                    PasarTurno()}
                else{
                    Toast.makeText(
                        this@MainActivity,
                        "No hay cartas", Toast.LENGTH_SHORT).show()


                    /* var contador = 0
                     while (Mesa.size<=1){
                         Mazo.add(Mesa[contador])
                         Log.i("ROBAR", " se agregar al Mazo: "+Mazo[contador].number)
                         Mesa.removeAt(contador)
                         contador++
                     }
                     pos=0
                     shuffle(Mazo,Mazo.size)*/
                }}

        }
        else if(contJugada==1){
            Log.i("ROBAR","NO SE PUEDE ROBAR")

            bRobar.isEnabled = false
        }
        else{
            bRobar.isEnabled = false
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

        JActual.text= jugadores[aTurno[0]].cant.toString()+ " cartas"
        JSiguiente.text=jugadores[aTurno[1]].cant.toString() + " cartas"
        JSiguienteSig.text=jugadores[aTurno[2]].cant.toString() + " cartas"

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        InicializaJuego()
        DuranteJuego()
    }
    fun unaCarta()  {
        Log.i("UnaCarta",jugadores[0].cant.toString() )
        for(i in 0..2){
            if(jugadores[i].cant == 1){
                val num = i +1
                Toast.makeText(applicationContext,
                    "El jugador "+ num +" le queda una carta",Toast.LENGTH_SHORT).show()
            }
        }
    }

}