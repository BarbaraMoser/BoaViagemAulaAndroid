package com.example.boaviagem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.framePrincipal, HomeActivity())
            .commit()

        val navegacao = findViewById<BottomNavigationView>(R.id.navegacao)
        navegacao.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.fragmento_home -> createFragment(HomeActivity())
                R.id.fragmento_nova_viagem -> createFragment(NovaViagem())
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