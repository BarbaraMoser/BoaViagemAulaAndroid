package com.example.boaviagem.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Viagem (val destino: String,
                   val tipo: String,
                   val data_chegada: String,
                   val data_partida: String,
                   val orcamento: String,
                   val id_usuario: String) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}