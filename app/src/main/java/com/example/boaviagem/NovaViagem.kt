package com.example.boaviagem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.example.boaviagem.dao.ViagemDao
import com.example.boaviagem.database.AppDatabase
import com.example.boaviagem.model.Viagem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class NovaViagem(val usuario_id: String) : Fragment() {

    lateinit var viagemDao: ViagemDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.let {
            val view = inflater.inflate(R.layout.activity_nova_viagem, container, false)
            salvar_nova_viagem(view)
        }
        return view
    }

    fun salvar_nova_viagem(view: View) {
        activity?.let {
            val destino =
                view.findViewById<EditText>(com.example.boaviagem.R.id.destino).text.toString()
            val tipo =
                view.findViewById<Spinner>(com.example.boaviagem.R.id.spinner_tipo_viagem).onItemClickListener.toString()
            val data_chegada =
                view.findViewById<EditText>(com.example.boaviagem.R.id.data_picker_chegada).text.toString()
            val data_partida =
                view.findViewById<EditText>(com.example.boaviagem.R.id.data_picker_partida).text.toString()
            val orcamento =
                view.findViewById<EditText>(com.example.boaviagem.R.id.orcamento_viagem).text.toString()
            val viagem = Viagem(destino, tipo, data_chegada, data_partida, orcamento, usuario_id)

            GlobalScope.launch(Dispatchers.Main) {
                withContext(Dispatchers.IO) {
                    viagemDao.insert(viagem)
                }
            }
        }
    }
}