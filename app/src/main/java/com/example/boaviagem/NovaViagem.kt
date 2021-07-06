package com.example.boaviagem

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Spinner
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.boaviagem.dao.ViagemDao
import com.example.boaviagem.database.AppDatabase
import com.example.boaviagem.domains.Viagem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*


class NovaViagem() : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.let {
            val view = inflater.inflate(R.layout.activity_nova_viagem, container, false)
//            val button = view.findViewById<Button>(R.id.salvar_nova_viagem)
//            button.setOnClickListener {
//                salvar_nova_viagem(view)
//            }
        }
        return view
    }

    fun salvar_nova_viagem(view: View) {
        activity?.let {
            val destino =
                view.findViewById<EditText>(com.example.boaviagem.R.id.destino).text.toString()
            val tipo =
                view.findViewById<Spinner>(com.example.boaviagem.R.id.spinner_tipo_viagem).onItemClickListener.toString()
            val data_chegada = get_date(view.findViewById<DatePicker>(com.example.boaviagem.R.id.data_picker_chegada))
            val data_partida = get_date(view.findViewById<DatePicker>(com.example.boaviagem.R.id.data_picker_partida))
            val orcamento =
                view.findViewById<EditText>(com.example.boaviagem.R.id.orcamento_viagem).text.toString()
            val usuario_id = 1
            val viagem = Viagem(
                destino,
                tipo,
                data_chegada,
                data_partida,
                orcamento,
                usuario_id
            )

            GlobalScope.launch(Dispatchers.Main) {
                withContext(Dispatchers.IO) {
                    AppDatabase.getInstance(it).viagemDao().insert(viagem)
                }
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun get_date(datePicker: DatePicker): String {
        val day: Int = datePicker.dayOfMonth
        val month: Int = datePicker.month + 1
        val year: Int = datePicker.year
        val dateFormatter = SimpleDateFormat("MM-dd-yyyy")
        val d = Date(year, month, day)
        return dateFormatter.format(d)
    }
}