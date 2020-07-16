package com.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.convidados.service.model.GuestModel
import com.convidados.service.repository.GuestRepository

class GuestViewModel(application: Application) : AndroidViewModel(application) {

    private val mGuestRepository = GuestRepository(application.applicationContext)

    private val mGuestList = MutableLiveData<List<GuestModel>>()
    val guestList: LiveData<List<GuestModel>> = mGuestList

    fun load(filter : String){

        mGuestList.value = when(filter){
            "ALL"->   mGuestRepository.getAll()
            "PRESENTS" ->  mGuestRepository.getPresent()
            "ABSENTS" -> mGuestRepository.getAbsent()
            else -> null
        }

    }

    fun delete(id: Int) {

        val guest = mGuestRepository.findById(id)

        mGuestRepository.delete(guest)

    }
}