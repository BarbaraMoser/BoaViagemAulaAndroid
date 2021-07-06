package com.example.boaviagem.daodestino

import androidx.room.*
import com.example.boaviagem.domains.Gasto

@Dao
interface GastoDao {

    @Insert
    fun insert(gasto: Gasto)

    @Update
    fun update(gasto: Gasto)

    @Delete
    fun delete(gasto: Gasto)

    @Query("select * from Gasto order by tipo, local")
    fun findAll(): List<Gasto>

    @Query("select * from Gasto where id=:id")
    fun findById(id: Int): Gasto
}