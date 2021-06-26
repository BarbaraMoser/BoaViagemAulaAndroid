package com.example.boaviagem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.boaviagem.daodestino.GastoDao
import com.example.boaviagem.model.Gasto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NovoGasto(val viagem_id: Int) : Fragment() {

    lateinit var gastoDao: GastoDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val db = Room.databaseBuilder<AppDatabase?>(
//            this,
//            AppDatabase::class.java,
//            "boa_viagem_db"
//        ).build()
//        gastoDao = db.gastoDao()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            com.example.boaviagem.R.layout.activity_novo_gasto,
            container,
            false
        )
    }

    fun salvar_novo_gasto(view: View) {
        val tipo = view.findViewById<EditText>(com.example.boaviagem.R.id.spinner_tipo_gasto).text.toString()
        val valor = view.findViewById<EditText>(com.example.boaviagem.R.id.valor).text.toString()
        val data = view.findViewById<EditText>(com.example.boaviagem.R.id.data_picker_gasto).text.toString()
        val descricao = view.findViewById<EditText>(com.example.boaviagem.R.id.descricao).text.toString()
        val local = view.findViewById<EditText>(com.example.boaviagem.R.id.local_gasto).text.toString()
        val gasto = Gasto(tipo.toInt(), valor.toFloat(), data, descricao, local, viagem_id)

        GlobalScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                gastoDao.insert(gasto)
            }
        }
    }

}