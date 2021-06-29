package com.example.boaviagem.dao

import androidx.room.*
import com.example.boaviagem.model.Viagem

@Dao
interface ViagemDao {

    @Insert
    fun insert(viagem: Viagem)

    @Update
    fun update(viagem: Viagem)

    @Delete
    fun delete(viagem: Viagem)

    @Query("select * from Viagem order by tipo, destino")
    fun findAll(): List<Viagem>

    @Query("select * from Viagem where id=:id")
    fun findById(id: Int): Viagem
}