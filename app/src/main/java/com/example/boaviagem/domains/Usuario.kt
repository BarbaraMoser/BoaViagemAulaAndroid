package com.example.boaviagem.domains

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Usuario(val nome: String,
                   val email: String,
                   val password: String) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}