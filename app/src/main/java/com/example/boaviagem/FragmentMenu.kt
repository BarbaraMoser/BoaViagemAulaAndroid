package com.example.boaviagem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class FragmentMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_menu)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmento_home, HomeActivity())
            .commit()

        val navegacao = findViewById<BottomNavigationView>(R.id.navegacao)
        navegacao.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.fragmento_home -> createFragment(HomeActivity())
                R.id.fragmento_nova_viagem -> createFragment(NovaViagem())
                R.id.fragmento_novo_gasto -> createFragment(NovoGasto())
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