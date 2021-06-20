package com.example.boaviagem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.example.boaviagem.daodestino.ViagemDao
import com.example.boaviagem.database.AppDatabase
import com.example.boaviagem.model.Viagem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class NovaViagem : Fragment() {

    lateinit var viagemDao: ViagemDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder<AppDatabase?>(
            this,
            AppDatabase::class.java,
            "boa_viagem_db"
        ).build()
        viagemDao = db.viagemDao()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            com.example.boaviagem.R.layout.activity_nova_viagem,
            container,
            false
        )
    }

    fun salvar_nova_viagem(view: View) {
        val destino = findViewById<EditText>(com.example.boaviagem.R.id.destino).text.toString()
        val tipo =
            findViewById<EditText>(com.example.boaviagem.R.id.spinner_tipo_viagem).text.toString()
        val data_chegada =
            findViewById<EditText>(com.example.boaviagem.R.id.data_picker_chegada).text.toString()
        val data_partida =
            findViewById<EditText>(com.example.boaviagem.R.id.data_picker_partida).text.toString()
        val orcamento =
            findViewById<EditText>(com.example.boaviagem.R.id.orcamento_viagem).text.toString()
        val id_usuario =
            findViewById<EditText>(com.example.boaviagem.R.id.id_usuario_nova_viagem).text.toString()
        val viagem = Viagem(destino, tipo, data_chegada, data_partida, orcamento, id_usuario)

        GlobalScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                viagemDao.insert(viagem)
            }
        }
    }
}