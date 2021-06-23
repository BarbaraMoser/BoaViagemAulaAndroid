package com.example.boaviagem

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.boaviagem.daodestino.ViagemDao
import com.example.boaviagem.database.AppDatabase
import com.example.boaviagem.model.Viagem
import com.example.boaviagem.model.ViagemAdapter


class HomeActivity(val usuario_id: String) : Fragment() {

    lateinit var viagemDao: ViagemDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.activity_home, container, false)
        val recycler_view = view.findViewById<RecyclerView>(R.id.lista_viagens)
        val list = buscar_viagens()
        val adapter = ViagemAdapter(list)
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this)

        adapter.onItemClick = {
            val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransacition()
            fragmentTransaction.replace(R.id.novo_gasto_layout, NovoGasto(it.id)).commit()
        }

        return view
    }

    fun buscar_viagens(): List<Viagem> {
        val db = Room.databaseBuilder<AppDatabase?>(
            this,
            AppDatabase::class.java,
            "boa_viagem_db"
        ).build()
        viagemDao = db.viagemDao()
        return viagemDao.findAll()
    }

//    fun gastos_viagem() {
//        supportFragmentManager
//            .beginTransaction()
//            .add(R.id.fragmento_home, HomeActivity(id_usuario))
//            .commit()
//
//        val navegacao = findViewById<BottomNavigationView>(R.id.navegacao)
//        navegacao.setOnNavigationItemSelectedListener {
//            when (it.itemId) {
//                R.id.fragmento_home -> createFragment(HomeActivity(id_usuario))
//                R.id.fragmento_nova_viagem -> createFragment(NovaViagem(id_usuario))
//                else -> false
//            }
//        }
//    }
//
//    private fun createFragment(fragment: Fragment): Boolean {
//        supportFragmentManager
//            .beginTransaction()
//            .replace(R.id.framePrincipal, fragment)
//            .addToBackStack(null)
//            .commit()
//        return true
//    }

}