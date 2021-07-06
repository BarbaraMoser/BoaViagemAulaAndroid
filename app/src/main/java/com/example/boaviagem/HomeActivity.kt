package com.example.boaviagem


import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
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

    private lateinit var ctx: Context

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.ctx = container?.context!!
        val view = inflater.inflate(R.layout.activity_home, container, false)
        buscar_viagens(view)
        return view
    }

    @SuppressLint("ResourceType")
    fun buscar_viagens(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.lista_viagens)
        GlobalScope.launch(Dispatchers.Main) {
            val viagens = withContext(Dispatchers.IO) {
                AppDatabase.getInstance(requireContext()).viagemDao().getViagens()
            }
            Log.i("Buscar Viagens", "${viagens}")
            val adapter = ViagemAdapter(viagens)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())

            adapter.onItemClick = {
                Log.i("Click viagem", "passou aqui 2222")
                val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.framePrincipal, NovaViagem()).commit()
            }
        }
    }
}