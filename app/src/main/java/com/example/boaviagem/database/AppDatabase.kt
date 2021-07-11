package com.example.boaviagem.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.boaviagem.dao.UsuarioDao
import com.example.boaviagem.dao.ViagemDao
import com.example.boaviagem.daodestino.GastoDao
import com.example.boaviagem.domains.Gasto
import com.example.boaviagem.domains.Usuario
import com.example.boaviagem.domains.Viagem

@Database(
    entities = arrayOf(Usuario::class, Viagem::class, Gasto::class),
    version = 5,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao
    abstract fun viagemDao(): ViagemDao
    abstract fun gastoDao(): GastoDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE != null) {
                return INSTANCE as AppDatabase
            }
            synchronized(this) {
                val INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "boa_viagem_db"
                ).fallbackToDestructiveMigration().build()
                return INSTANCE
            }
        }
    }
}


