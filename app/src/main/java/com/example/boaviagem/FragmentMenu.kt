package com.example.boaviagem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class FragmentMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_menu)

        val id_usuario: String = intent.extras?.get("usuario").toString()

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmento_home, HomeActivity(id_usuario))
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
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.framePrincipal, fragment)
            .addToBackStack(null)
            .commit()
        return true
    }
}