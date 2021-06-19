package com.example.boaviagem.dao

import androidx.room.*
import com.example.boaviagem.model.Usuario

@Dao
interface UsuarioDao {

    @Insert
    fun insert(usuario: Usuario)

    @Update
    fun update(usuario: Usuario)

    @Delete
    fun delete(usuario: Usuario)

    @Query("select * from Usuario order by nome")
    fun findAll(): List<Usuario>

    @Query("select * from Usuario where id=:id")
    fun findById(id: Int): Usuario

    @Query("select * from Usuario where email=:email and password=:password")
    fun login(email: String, password: String): Usuario?
}