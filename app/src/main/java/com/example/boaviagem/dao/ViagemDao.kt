package com.example.boaviagem.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.boaviagem.domains.Viagem

@Dao
interface ViagemDao {

    @Query("select * from Viagem where id_usuario = :id_usuario order by tipo, destino asc")
    fun getViagens(id_usuario: String): List<Viagem>

    @Query("select * from Viagem where id = :id")
    fun getViagemById(id: String): Viagem

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(viagem: Viagem)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun udpate(viagem: Viagem)

    @Query("delete from Viagem")
    suspend fun deleteTodas()

    @Delete()
    suspend fun delete(viagem: Viagem)

}