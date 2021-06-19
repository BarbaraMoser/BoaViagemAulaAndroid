package com.example.boaviagem

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.boaviagem.dao.UsuarioDao
import com.example.boaviagem.database.AppDatabase
import com.example.boaviagem.model.Usuario
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterActivity : AppCompatActivity() {

    lateinit var usuarioDao: UsuarioDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val db = Room.databaseBuilder(this, AppDatabase::class.java, "boa_viagem_db").build()
        usuarioDao = db.usuarioDao()
    }

    fun salvar_usuario(view: View) {
        val nome = findViewById<EditText>(R.id.nome_registro).text.toString()
        val email = findViewById<EditText>(R.id.email_registro).text.toString()
        val password = findViewById<EditText>(R.id.password_registro).text.toString()
        val usuario = Usuario(nome, email, password)

        GlobalScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                usuarioDao.insert(usuario)
            }
        }

        Intent(this, HomeActivity::class.java).apply {
            putExtra("usuario", usuario.id);
            startActivity(this)
        }
    }
}