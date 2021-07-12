package com.example.boaviagem.view

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.boaviagem.R
import com.example.boaviagem.adapter.GastoAdapter
import com.example.boaviagem.database.AppDatabase
import com.example.boaviagem.domains.Viagem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListaGastos(private var viagem: Viagem) : Fragment() {
    private lateinit var ctx: Context

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.ctx = container?.context!!
        val view = inflater.inflate(R.layout.activity_lista_gastos, container, false)

        buscar_gastos(view)
        view.findViewById<Button>(R.id.adicionar_gasto).setOnClickListener {
            adicionar_gasto(view)
        }

        return view
    }

    @SuppressLint("ResourceType", "SetTextI18n")
    fun buscar_gastos(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.lista_gastos)

        GlobalScope.launch(Dispatchers.Main) {
            val gastos = withContext(Dispatchers.IO) {
                AppDatabase.getInstance(requireContext()).gastoDao().getGastos(viagem.id)
            }

            if (gastos.isEmpty()) {
                val sem_gastos = view.findViewById<TextView>(R.id.sem_gastos)
                sem_gastos.text = "Não há gastos cadastrados"
            }

            val adapter = GastoAdapter(gastos)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())

            adapter.onItemClick = {
                val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(
                    R.id.framePrincipal,
                    EditarGastos(it, viagem)
                ).commit()
            }
        }
    }

    fun adicionar_gasto(view: View) {
        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(
            R.id.framePrincipal,
            NovoGasto(viagem)
        ).commit()
    }
}