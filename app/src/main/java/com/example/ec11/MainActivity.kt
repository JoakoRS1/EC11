package com.example.ec11

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ec11.Views.*
import java.util.*


class MainActivity : AppCompatActivity() {
    //var Carta: Carta? = null
    var jugadores = mutableListOf<Jugador>()

    private var Mazo = mutableListOf<Carta>()
    private var CartasenMazo: Int=52


    fun InicializaJuego(){
        var jugador1 = Jugador(this,1,0)
        var jugador2 = Jugador(this,2,0)
        var jugador3 = Jugador(this,3,0)

        var areamesa = findViewById<LinearLayout>(R.id.areaCartaMesa);

        jugadores.add(jugador1)
        jugadores.add(jugador2)
        jugadores.add(jugador3)

        repartirInicial()

    }

    fun DuranteJuego(){

        val recyclerView = findViewById<RecyclerView> (R.id.my_recycler_view)

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView.layoutManager = layoutManager

        val adapter = CustomAdapter(jugadores[0].subMazo)//CAMBIARRR
        recyclerView.adapter = adapter




        imprimirTextos();
        PasarTurno()
        RobarCarta()

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
                jugadores[j].subMazo.add(robarCarta())
                jugadores[j].cant++
        }   }
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

    /*fun crearVistaCarta(CardView: Carta){
        CardView.number= Mazo[3].number
        CardView.palo=Mazo[3].palo
    }*/

    fun robarCarta (): Carta{
        //-1 Mazo principal
        var carta_repartida=Mazo.removeAt(0)
        CartasenMazo-=1
        return carta_repartida
    }

    fun PasarTurno(){
        val bPasar = findViewById<Button>(R.id.bPasar);
        bPasar.setOnClickListener{
            val a= findViewById<TextView>(R.id.J3Total)//PRUEBA
            a.text="PASAAA"//PRUEBA
        }
    }

    fun RobarCarta(){
        val bRobar = findViewById<Button>(R.id.bRobar);
        bRobar.setOnClickListener{
            val a= findViewById<TextView>(R.id.J3Total)//PRUEBA
            a.text="ROBOO0"//PRUEBA
        }
    }



    fun imprimirTextos(){
        val TvCartasenMazo= findViewById<TextView>(R.id.TVCartasEnMAzo)
        val TvJ1CartasMazo= findViewById<TextView>(R.id.J1Total)
        val TvJ2CartasMazo= findViewById<TextView>(R.id.J2Total)
        val TvJ3CartasMazo= findViewById<TextView>(R.id.J3Total)


        TvCartasenMazo.text=CartasenMazo.toString()
        TvJ1CartasMazo.text=jugadores[0].cant.toString()
        TvJ2CartasMazo.text=jugadores[1].cant.toString()
        TvJ3CartasMazo.text=jugadores[2].cant.toString()
    }





    class CustomAdapter(private val dataSet: MutableList<Carta>): RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val textView: Carta=view.findViewById(R.id.carta)
        }
        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
            // Create a new view, which defines the UI of the list item
            val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.carta_mazo, viewGroup, false)
            return ViewHolder(view)
        }
        // Replace the contents of a view (invoked by the layout manager)
        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
            // Get element from your dataset at this position and replace the
            // contents of the view with that element
            viewHolder.textView.number = dataSet[position].number
            viewHolder.textView.palo = dataSet[position].palo
        }
        // Return the size of your dataset (invoked by the layout manager)
        override fun getItemCount() = dataSet.size
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        InicializaJuego()
        DuranteJuego()
    }

}