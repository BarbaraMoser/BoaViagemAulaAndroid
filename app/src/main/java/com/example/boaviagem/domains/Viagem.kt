package com.example.boaviagem.domains

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Viagem(
    var destino: String,
    var tipo: String,
    var data_chegada: String,
    var data_partida: String,
    var orcamento: String,
    var id_usuario: String
) {

    @PrimaryKey(autoGenerate = false)
    var id: String = UUID.randomUUID().toString();
}