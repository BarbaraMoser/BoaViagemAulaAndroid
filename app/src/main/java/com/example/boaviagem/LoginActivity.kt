package com.example.boaviagem

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.boaviagem.database.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun efetuar_login(view: View) {
        val email = findViewById<EditText>(R.id.email).text.toString()
        val password = findViewById<EditText>(R.id.password).text.toString()
        var id = "1"

        GlobalScope.launch(Dispatchers.Main) {

            val usuario = withContext(Dispatchers.IO) {
                AppDatabase.getInstance(this@LoginActivity).usuarioDao().login(email, password)
            }
            Log.i("Global Login", "Usuário ${usuario}")
            if (usuario != null) {
                Intent(this@LoginActivity, MenuActivity::class.java).apply {
                    putExtra("usuario", id);
                    startActivity(this)
                }
            } else {
                Toast.makeText(this@LoginActivity, "Usuário e/ou senha incorretos!", Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    fun registrar_usuario(view: View) {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

}
