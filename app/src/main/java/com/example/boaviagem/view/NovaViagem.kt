package com.example.boaviagem.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.boaviagem.R
import com.example.boaviagem.database.AppDatabase
import com.example.boaviagem.domains.Viagem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class NovaViagem(id_usuario: String) : Fragment() {

    private lateinit var ctx: Context
    private lateinit var exc: ClassNotFoundException
    private var usuario: String = id_usuario

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_nova_viagem, container, false)
        this.ctx = container?.context!!
        view.findViewById<Button>(R.id.salvar_nova_viagem).setOnClickListener {
            salvar_nova_viagem(view)
        }
        return view
    }

    fun salvar_nova_viagem(view: View) {
        val destino =
            view.findViewById<EditText>(R.id.destino).text.toString()
        val tipo =
            view.findViewById<EditText>(R.id.tipo).text.toString()
        val data_chegada =
            get_date(view.findViewById<DatePicker>(R.id.data_picker_chegada))
        val data_partida =
            get_date(view.findViewById<DatePicker>(R.id.data_picker_partida))
        val orcamento =
            view.findViewById<EditText>(R.id.orcamento_viagem).text.toString()
        val viagem = Viagem(
            destino,
            tipo,
            data_chegada,
            data_partida,
            orcamento,
            usuario
        )

        GlobalScope.launch(Dispatchers.Main) {
            var e: ClassNotFoundException
            try {
                withContext(Dispatchers.IO) {
                    AppDatabase.getInstance(ctx).viagemDao().insert(viagem)
                }
            } catch (exc: ClassNotFoundException) {
                Toast.makeText(ctx, "Não foi possível salvar a viagem: ${exc}", Toast.LENGTH_SHORT)
                    .show()
            } finally {
                Intent(ctx, MenuActivity::class.java).apply {
                    startActivity(this)
                }
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun get_date(datePicker: DatePicker): String {
        val day: Int = datePicker.dayOfMonth
        val month: Int = datePicker.month + 1
        val year: Int = datePicker.year
        return "${day}-${month}-${year}"
    }

}