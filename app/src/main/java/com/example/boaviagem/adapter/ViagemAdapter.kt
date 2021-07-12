package com.example.boaviagem.adapter
//
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.boaviagem.R
import com.example.boaviagem.domains.Viagem

class ViagemAdapter(val item: List<Viagem>) : RecyclerView.Adapter<ViagemAdapter.ViewHolder>() {

    private lateinit var ctx: Context
    var onItemClick: ((Viagem) -> Unit)? = null

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val tipo_viagem: TextView = view.findViewById<TextView>(R.id.text_tipo_viagem)
        private val destino: TextView = view.findViewById<TextView>(R.id.text_destino)
        private val chegada: TextView = view.findViewById<TextView>(R.id.text_chegada)

        init {
            view.findViewById<CardView>(R.id.item_viagem_card_view).setOnClickListener {
                onItemClick?.invoke(item[adapterPosition])
            }
        }

        fun bind(v: Viagem) {
            if (v.tipo.toUpperCase() == "LAZER") {
                view.findViewById<ImageView>(R.id.imagem_tipo_viagem).apply {
                    setImageResource(R.drawable.lazer_icon)
                }
            } else if (v.tipo.toUpperCase() == "NEGÓCIO") {
                view.findViewById<ImageView>(R.id.imagem_tipo_viagem).apply {
                    setImageResource(R.drawable.negocio_icon)
                }
            }
            tipo_viagem.text = v.tipo
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