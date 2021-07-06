package com.example.boaviagem.adapter

import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnCreateContextMenuListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.boaviagem.R
import com.example.boaviagem.domains.Viagem


class ViagemAdapter() : RecyclerView.Adapter<ViagemAdapter.ViagemViewHolder>() {

    var viagens = emptyList<Viagem>()

    var onItemClick: ((Viagem) -> Unit)? = null

    var onAlterar: ((Viagem) -> Boolean)? = null

    var onExcluir: ((Viagem) -> Boolean)? = null

    inner class ViagemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        OnCreateContextMenuListener {
        val tipoViagem: TextView = itemView.findViewById<TextView>(R.id.text_tipo_viagem)
        val destino: TextView = itemView.findViewById<TextView>(R.id.text_destino)
        val chegada: TextView = itemView.findViewById<TextView>(R.id.text_chegada)

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(viagens.get(adapterPosition))
            }
            itemView.setOnCreateContextMenuListener(this)
        }

        override fun onCreateContextMenu(
            menu: ContextMenu?,
            v: View?,
            menuInfo: ContextMenu.ContextMenuInfo?
        ) {
            menu?.setHeaderTitle("Opções")
            val alterar = menu?.add("Alterar")
            val excluir = menu?.add("Excluir")
            alterar?.setOnMenuItemClickListener {
                onAlterar?.invoke(viagens.get(adapterPosition)) ?: false
            }
            excluir?.setOnMenuItemClickListener {
                onExcluir?.invoke(viagens.get(adapterPosition)) ?: false
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViagemViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_viagem, parent, false)
        return ViagemViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return viagens.size
    }

    override fun onBindViewHolder(holder: ViagemViewHolder, position: Int) {
        holder.tipoViagem.text = viagens.get(position).tipo
        holder.destino.text = viagens.get(position).destino
        holder.chegada.text = viagens.get(position).data_chegada
    }

    internal fun setViagens(viagens: List<Viagem>) {
        this.viagens = viagens
        notifyDataSetChanged()
    }
}