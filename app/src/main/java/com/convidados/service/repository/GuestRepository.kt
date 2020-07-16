package com.convidados.service.repository

import android.content.ContentValues
import android.content.Context
import com.convidados.service.model.GuestModel
import java.lang.Exception

class GuestRepository (context: Context) {

    private val mDataBase = GuestDataBase.getDataBase(context).guestDAO()

    fun save(guest: GuestModel): Boolean {
        return mDataBase.save(guest) > 0
    }

    fun update(guest: GuestModel): Boolean {
        return mDataBase.update(guest) > 0
    }

    fun delete(guest: GuestModel) {
        mDataBase.delete(guest)
    }

    fun findById(id: Int): GuestModel {

        return mDataBase.findById(id)
    }

    fun getAll(): List<GuestModel> {

        return mDataBase.getAll()
    }

    fun getPresent(): List<GuestModel> {
        return mDataBase.getPresent()
    }

    fun getAbsent(): List<GuestModel> {
        return mDataBase.getAbsent()
    }
}