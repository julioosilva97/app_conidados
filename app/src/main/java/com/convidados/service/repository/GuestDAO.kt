package com.convidados.service.repository

import androidx.room.*
import com.convidados.service.model.GuestModel

@Dao
interface GuestDAO {

    @Insert
    fun save(guest:GuestModel) : Long // Long é o id q foi inserido

    @Update
    fun update(guest:GuestModel) : Int // Int , numero de registros no banco

    @Delete
    fun delete(guest:GuestModel)

    @Query("select * from guest where id = :id")
    fun findById(id: Int): GuestModel

    @Query("select * from guest")
    fun getAll(): List<GuestModel>

    @Query("select * from guest where presence = 0")
    fun getPresent(): List<GuestModel>

    @Query("select * from guest where presence = 0")
    fun getAbsent(): List<GuestModel>

}