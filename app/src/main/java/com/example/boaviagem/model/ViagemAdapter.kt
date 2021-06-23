package com.example.boaviagem.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.boaviagem.R

class ViagemAdapter(val item: List<Viagem>) : RecyclerView.Adapter<ViagemAdapter.ViewHolder>() {

    var onItemClick: ((Viagem) -> Unit)? = null

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val tipoViagem: TextView
        private val destino: TextView
        private val chegada: TextView

        init {
            tipoViagem = view.findViewById<TextView>(R.id.text_tipo_viagem)
            destino = view.findViewById<TextView>(R.id.text_destino)
            chegada = view.findViewById<TextView>(R.id.text_chegada)
            view.findViewById<CardView>(R.id.item_viagem_card_view).setOnClickListener {
                onItemClick?.invoke(item[adapterPosition])
            }
        }

        fun bind(v: Viagem) {
            tipoViagem.text = v.tipo
            destino.text = v.destino
            chegada.text = v.data_chegada
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_viagem, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return item.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(item[position])
    }
}