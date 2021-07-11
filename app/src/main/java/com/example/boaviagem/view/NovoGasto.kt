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
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.boaviagem.R
import com.example.boaviagem.database.AppDatabase
import com.example.boaviagem.domains.Gasto
import com.example.boaviagem.domains.Viagem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NovoGasto(viagem: Viagem) : Fragment() {

    private lateinit var ctx: Context
    private var id_viagem: String = viagem.id
    private var viagem: Viagem = viagem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_novo_gasto, container, false)
        this.ctx = container?.context!!
        view.findViewById<Button>(R.id.salvar_novo_gasto).setOnClickListener {
            salvar_novo_gasto(view)
        }

        view.findViewById<Button>(R.id.voltar_novo_gasto).setOnClickListener {
            voltar_menu_principal(view)
        }
        return view
    }

    fun salvar_novo_gasto(view: View) {
        val tipo = view.findViewById<EditText>(R.id.tipo_gasto).text.toString()
        val valor = view.findViewById<EditText>(R.id.valor).text.toString()
        val data = get_date(view.findViewById<DatePicker>(R.id.data_picker_gasto))
        val descricao = view.findViewById<EditText>(R.id.descricao).text.toString()
        val local = view.findViewById<EditText>(R.id.local_gasto).text.toString()
        val gasto = Gasto(
            tipo,
            valor.toFloat(),
            data,
            descricao,
            local,
            id_viagem
        )

        GlobalScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                AppDatabase.getInstance(ctx).gastoDao().insert(gasto)
            }
        }
    }

    fun voltar_menu_principal(view: View) {
        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(
            R.id.framePrincipal,
            ListaGastos(viagem)
        ).commit()
    }

    @SuppressLint("SimpleDateFormat")
    fun get_date(datePicker: DatePicker): String {
        val day: Int = datePicker.dayOfMonth
        val month: Int = datePicker.month + 1
        val year: Int = datePicker.year
        return "${day}-${month}-${year}"
    }

}