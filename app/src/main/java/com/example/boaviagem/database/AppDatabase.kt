package com.example.boaviagem.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.boaviagem.dao.UsuarioDao
import com.example.boaviagem.daodestino.GastoDao
import com.example.boaviagem.dao.ViagemDao
import com.example.boaviagem.model.Gasto
import com.example.boaviagem.model.Usuario
import com.example.boaviagem.model.Viagem

@Database(entities = arrayOf(Usuario::class, Viagem::class, Gasto::class), version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao
    abstract fun viagemDao(): ViagemDao
    abstract fun gastoDao(): GastoDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "boa_viagem_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE as AppDatabase
        }
    }


}


