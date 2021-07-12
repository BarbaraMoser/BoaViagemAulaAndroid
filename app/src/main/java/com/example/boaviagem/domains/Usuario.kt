package com.example.boaviagem.domains

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Usuario(val nome: String,
                   val email: String,
                   val password: String) {

    @PrimaryKey(autoGenerate = false)
    var id: String = UUID.randomUUID().toString();
}