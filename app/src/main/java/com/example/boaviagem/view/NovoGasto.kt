package com.example.boaviagem.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.boaviagem.R
import com.example.boaviagem.daodestino.GastoDao
import com.example.boaviagem.domains.Gasto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NovoGasto(viagem_id: String) : Fragment() {

    lateinit var gastoDao: GastoDao
    private var viagem: String = viagem_id

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
            R.layout.activity_novo_gasto,
            container,
            false
        )
    }

    fun salvar_novo_gasto(view: View) {
        val tipo = view.findViewById<EditText>(R.id.spinner_tipo_gasto).text.toString()
        val valor = view.findViewById<EditText>(R.id.valor).text.toString()
        val data = view.findViewById<EditText>(R.id.data_picker_gasto).text.toString()
        val descricao = view.findViewById<EditText>(R.id.descricao).text.toString()
        val local = view.findViewById<EditText>(R.id.local_gasto).text.toString()
        val gasto = Gasto(
            tipo.toInt(),
            valor.toFloat(),
            data,
            descricao,
            local,
            viagem
        )

        GlobalScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                gastoDao.insert(gasto)
            }
        }
    }

}