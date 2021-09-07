package com.dicoding.panbas.ui.home

import androidx.lifecycle.ViewModel
import com.dicoding.panbas.data.repository.banjir.BanjirRepository

class HomeViewModel (
    private val banjirRepository:BanjirRepository
): ViewModel(){
    fun getAllBanjir() = banjirRepository.getAllBanjir()
}
