package com.example.boaviagem.adapter
//
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.boaviagem.R
import com.example.boaviagem.domains.Gasto

class GastoAdapter(val item: List<Gasto>) : RecyclerView.Adapter<GastoAdapter.ViewHolder>() {

    private lateinit var ctx: Context
    var onItemClick: ((Gasto) -> Unit)? = null

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val descricao_gasto: TextView =
            view.findViewById<TextView>(R.id.text_descricao_gasto)
        private val valor_gasto: TextView = view.findViewById<TextView>(R.id.text_valor_gasto)

        init {
            view.findViewById<CardView>(R.id.item_gastos_card_view).setOnClickListener {
                onItemClick?.invoke(item[adapterPosition])
            }
        }

        fun bind(g: Gasto) {
            descricao_gasto.text = g.tipo
            valor_gasto.text = g.valor.toString()
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