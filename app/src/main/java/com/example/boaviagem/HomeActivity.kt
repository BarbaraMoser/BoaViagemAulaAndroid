package com.example.boaviagem


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.boaviagem.adapter.ViagemAdapter
import com.example.boaviagem.database.AppDatabase
import com.example.boaviagem.domains.Viagem
import com.example.boaviagem.model.ViagemViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class HomeActivity : Fragment() {

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.let {
            val view = inflater.inflate(R.layout.activity_home, container, false)

//            val recyclerView = view.findViewById<RecyclerView>(R.id.lista_viagens)
//            val list = buscar_viagens()
//            val adapter = ViagemAdapter()
//
//            list?.let { it1 -> adapter.setViagens(it1) }
//
//            recyclerView.adapter = adapter
//            recyclerView.layoutManager = LinearLayoutManager(it)

//            adapter?.onItemClick = {
//                val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
//                val fragmentTransaction = fragmentManager.beginTransaction()
//                fragmentTransaction.replace(R.layout.activity_novo_gasto, NovoGasto(it.id)).commit()
//            }
        }
        buscar_viagens(view)
        return view
    }

    @SuppressLint("ResourceType")
    fun buscar_viagens(view: View?) {
        activity?.let {

            val adapter = ViagemAdapter()
            val recyclerView = view?.findViewById<RecyclerView>(R.id.lista_viagens)

            var viagens = emptyList<Viagem>()
            activity?.let {
                GlobalScope.launch(Dispatchers.Main) {
                    viagens = withContext(Dispatchers.IO) {
                        AppDatabase.getInstance(it).viagemDao().getViagens()
                    }
                }
            }

            viagens?.let { it1 -> adapter.setViagens(it1) }

            recyclerView?.adapter = adapter
            recyclerView?.layoutManager = LinearLayoutManager(it)

            adapter?.onItemClick = {
                val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.layout.activity_novo_gasto, NovoGasto(it.id)).commit()
            }
        }
    }
}