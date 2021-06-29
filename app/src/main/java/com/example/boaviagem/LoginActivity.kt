package com.example.boaviagem

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.boaviagem.dao.UsuarioDao
import com.example.boaviagem.database.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class LoginActivity : AppCompatActivity() {

    lateinit var usuarioDao: UsuarioDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val db = Room.databaseBuilder(this, AppDatabase::class.java, "boa_viagem_db").build()

        usuarioDao = db.usuarioDao()
    }

    fun efetuar_login(view: View) {
        val email = findViewById<EditText>(R.id.email).text.toString()
        val password = findViewById<EditText>(R.id.password).text.toString()
        val id = "1234"

//        GlobalScope.launch(Dispatchers.Main) {
//            var usuario = withContext(Dispatchers.IO) {
//                AppDatabase.getInstance(this@LoginActivity).usuarioDao().login(email, password)
//            }
//        }

        Intent(this, FragmentMenu::class.java).apply {
            putExtra("usuario", id);
            startActivity(this)
        }
    }

    fun registrar_usuario(view: View) {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

}
