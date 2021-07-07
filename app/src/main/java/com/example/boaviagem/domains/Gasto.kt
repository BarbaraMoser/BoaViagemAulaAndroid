package com.example.boaviagem.domains

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Gasto(
    val tipo: Int,
    val valor: Float,
    val data: String,
    val descricao: String,
    val local: String,
    val id_viagem: String
) {

    @PrimaryKey(autoGenerate = false)
    var id: String = UUID.randomUUID().toString();
}