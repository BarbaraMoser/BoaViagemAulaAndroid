package com.example.boaviagem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.room.Room
import com.example.boaviagem.dao.UsuarioDao
import com.example.boaviagem.database.AppDatabase

class MainActivity : AppCompatActivity() {

    lateinit var usuarioDao: UsuarioDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = Room.databaseBuilder(this, AppDatabase::class.java, "boa_viagem_db").build()

        usuarioDao = db.usuarioDao()
    }

//    fun efetuar_login(view: View) {
//
//        val usuario = Usuario("Nome: ${Math.random()}", "email@senac.com.br", "1234")
//
//        GlobalScope.launch(Dispatchers.Main) {
//            val usuarios: List<Usuario> = withContext(Dispatchers.IO) {
//                usuarioDao.insert(usuario)
//                usuarioDao.findAll()
//            }
//
//            Toast.makeText(
//                this@MainActivity,
//                "Usuarios: ${usuarios}",
//                Toast.LENGTH_SHORT
//            ).show()
//        }
//    }

//    fun load(){
//        GlobalScope.launch(Dispatchers.Main) {
//            val usuarios = withContext(Dispatchers.IO) {
//                usuarioDao.insert(usuarios)
//                usuarioDao.findAll()
//            }
//            usuarioAdapter.items = usuarios
//        }
//    }

    fun entrar(view: View) {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}