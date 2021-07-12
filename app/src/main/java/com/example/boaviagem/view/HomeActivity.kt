package com.example.boaviagem.view


import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.boaviagem.R
import com.example.boaviagem.adapter.ViagemAdapter
import com.example.boaviagem.database.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class HomeActivity(id_usuario: String) : Fragment() {

    private lateinit var ctx: Context
    private var usuario: String = id_usuario

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

    @SuppressLint("ResourceType", "SetTextI18n")
    fun buscar_viagens(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.lista_viagens)

        GlobalScope.launch(Dispatchers.Main) {
            val viagens = withContext(Dispatchers.IO) {
                AppDatabase.getInstance(requireContext()).viagemDao().getViagens(usuario)
            }
            if (viagens.isEmpty()) {
                val sem_viagens = view.findViewById<TextView>(R.id.sem_viagens)
                sem_viagens.text = "Não há viagens cadastradas"
            }

            val adapter = ViagemAdapter(viagens)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())


            adapter.onItemClick = {
                val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(
                    R.id.framePrincipal,
                    EditarViagens(it)
                ).commit()
            }
        }
    }
}