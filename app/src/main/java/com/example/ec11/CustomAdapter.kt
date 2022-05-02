package com.example.ec11

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ec11.Views.Carta

class CustomAdapter(private val dataSet: MutableList<Carta>): RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    private lateinit var mListener2: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }
    fun setOnItemClickListener(listener: onItemClickListener){
        mListener2=listener
    }

    class ViewHolder(view: View, listener: onItemClickListener) : RecyclerView.ViewHolder(view) {
        val textView: Carta =view.findViewById(R.id.carta)
        init {
            view.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }

    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.carta_mazo, viewGroup, false)
        return ViewHolder(view,mListener2)
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