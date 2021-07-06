package com.example.boaviagem.domains

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Gasto(
    val tipo: Int,
    val valor: Float,
    val data: String,
    val descricao: String,
    val local: String,
    val id_viagem: Int
) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}