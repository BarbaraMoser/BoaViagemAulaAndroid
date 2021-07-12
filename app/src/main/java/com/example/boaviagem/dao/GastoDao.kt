package com.example.boaviagem.daodestino

import androidx.room.*
import com.example.boaviagem.domains.Gasto
import com.example.boaviagem.domains.Viagem

@Dao
interface GastoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(gasto: Gasto)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun udpate(gasto: Gasto)

    @Delete
    suspend fun delete(gasto: Gasto)

    @Query("select * from Gasto where id_viagem = :id_viagem order by tipo asc")
    fun getGastos(id_viagem: String): List<Gasto>

    @Query("select sum(valor) from Gasto where id_viagem = :id_viagem")
    fun getSomaGastos(id_viagem: String): Double
}