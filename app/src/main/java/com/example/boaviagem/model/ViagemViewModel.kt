package com.example.boaviagem.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.boaviagem.dao.ViagemDao
import com.example.boaviagem.database.AppDatabase
import com.example.boaviagem.domains.Viagem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViagemViewModel(application: Application) : AndroidViewModel(application) {

    private val viagemDao: ViagemDao
    val todasViagens: List<Viagem>
    val selected = MutableLiveData<Viagem>()

    init {
        viagemDao = AppDatabase.getInstance(application).viagemDao()
        todasViagens = viagemDao.getViagens()
    }

    fun save(viagem: Viagem) {
        viewModelScope.launch(Dispatchers.IO) {
            if (viagem.id == 0) {
                viagemDao.insert(viagem)
            } else {
                viagemDao.udpate(viagem)
            }
        }
    }

    fun select(viagem: Viagem) {
        this.selected.value = viagem
    }

    fun delete(viagem: Viagem) {
        viewModelScope.launch(Dispatchers.IO) {
            viagemDao.delete(viagem)
        }
    }
}