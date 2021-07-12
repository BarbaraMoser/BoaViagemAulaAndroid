package com.example.boaviagem.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.boaviagem.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val id_usuario: String = intent.extras?.get("usuario").toString()

        supportFragmentManager
            .beginTransaction()
            .add(R.id.framePrincipal, HomeActivity(id_usuario))
            .commit()

        val navegacao = findViewById<BottomNavigationView>(R.id.navegacao)
        navegacao.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.fragmento_home -> createFragment(HomeActivity(id_usuario))
                R.id.fragmento_nova_viagem -> createFragment(NovaViagem(id_usuario))
                else -> false
            }
        }
    }

    private fun createFragment(fragment: Fragment): Boolean {
        Log.i("CreateFragment Menu", "${fragment}")
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.framePrincipal, fragment)
            .addToBackStack(null)
            .commit()
        return true
    }
}