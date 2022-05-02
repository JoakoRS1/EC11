package com.example.ec11

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ec11.Views.*
import java.util.*


class MainActivity : AppCompatActivity() {


    var Carta: Carta? = null


    class Carta_Clase (val numero: Int, val palo: String){}
    var jugadores = mutableListOf<Jugador>()
    private var Mazo = mutableListOf<Carta_Clase>()
    private var SubMazo1 = mutableListOf<Carta_Clase>()
    private var SubMazo2 = mutableListOf<Carta_Clase>()
    private var SubMazo3 = mutableListOf<Carta_Clase>()
    private var CartasenMazo: Int=52
    private var J1CartasSubMazo: Int=0
    private var J2CartasSubMazo: Int=0
    private var J3CartasSubMazo: Int=0



    fun InicializaJuego(){
        var jugador1 = Jugador(this,1)
        var jugador2 = Jugador(this,2)
        var jugador3 = Jugador(this,3)

        var areamesa = findViewById<LinearLayout>(R.id.areaCartaMesa);

        jugadores.add(jugador1)
        jugadores.add(jugador2)
        jugadores.add(jugador3)

        areamesa.addView(jugador1)


    }

    fun DuranteJuego(){

    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView> (R.id.my_recycler_view)

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView.layoutManager = layoutManager

        val adapter = CustomAdapter(SubMazo1)
        recyclerView.adapter = adapter


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
        CardView.number= Mazo[3].numero
        CardView.palo=Mazo[3].palo
    }
    fun robarCarta (): Carta_Clase{
        //-1 Mazo principal
        var carta_repartida=Mazo.removeAt(0)
        CartasenMazo-=1
        return carta_repartida
    }



    class CustomAdapter(private val dataSet: MutableList<Carta_Clase>): RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val textView: Carta=view.findViewById(R.id.carta)

        }
        // Create new views (invoked by the layout manager)
        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
            // Create a new view, which defines the UI of the list item
            val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.carta_mazo, viewGroup, false)

            return ViewHolder(view)
        }
        // Replace the contents of a view (invoked by the layout manager)
        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
            // Get element from your dataset at this position and replace the
            // contents of the view with that element
            viewHolder.textView.number = dataSet[position].numero
            viewHolder.textView.palo = dataSet[position].palo
        }
        // Return the size of your dataset (invoked by the layout manager)
        override fun getItemCount() = dataSet.size

    }



}