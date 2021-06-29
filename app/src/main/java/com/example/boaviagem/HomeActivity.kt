package com.example.boaviagem


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.boaviagem.dao.ViagemDao
import com.example.boaviagem.database.AppDatabase
import com.example.boaviagem.model.Viagem
import com.example.boaviagem.model.ViagemAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class HomeActivity(val usuario_id: String) : Fragment() {

    lateinit var viagemDao: ViagemDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.let {
            val view = inflater.inflate(R.layout.activity_home, container, false)
            val recyclerView = view.findViewById<RecyclerView>(R.id.lista_viagens)
            val list = buscar_viagens()
            val adapter = list?.let { it1 -> ViagemAdapter(it1) }
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(it)

            adapter?.onItemClick = {
                val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.layout.activity_novo_gasto, NovoGasto(it.id)).commit()
            }
        }
        return view
    }

    fun buscar_viagens(): List<Viagem>? {
        var viagens = mutableListOf<Viagem>()
        activity?.let {
            GlobalScope.launch(Dispatchers.Main) {
                viagens = withContext(Dispatchers.IO) {
                    AppDatabase.getInstance(it).viagemDao().findAll()
                }.toMutableList()
            }
        }
        return viagens
    }
}