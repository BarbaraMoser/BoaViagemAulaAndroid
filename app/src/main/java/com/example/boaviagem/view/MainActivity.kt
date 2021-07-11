package com.example.boaviagem.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.boaviagem.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun entrar(view: View) {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}