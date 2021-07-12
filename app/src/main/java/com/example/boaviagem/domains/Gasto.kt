package com.example.boaviagem.domains

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Gasto(
    var tipo: String,
    var valor: Float,
    var data: String,
    var descricao: String,
    var local: String,
    var id_viagem: String
) {

    @PrimaryKey(autoGenerate = false)
    var id: String = UUID.randomUUID().toString();
}