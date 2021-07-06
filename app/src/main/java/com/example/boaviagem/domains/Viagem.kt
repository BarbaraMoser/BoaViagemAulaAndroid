package com.example.boaviagem.domains

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Viagem(
    var destino: String,
    var tipo: String,
    var data_chegada: String,
    var data_partida: String,
    var orcamento: String,
    var id_usuario: Int
) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}