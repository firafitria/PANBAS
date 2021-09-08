package com.dicoding.panbas.ui.detail

import androidx.lifecycle.ViewModel
import com.dicoding.panbas.data.datasource.local.entity.BanjirEntity
import com.dicoding.panbas.data.repository.banjir.BanjirRepository

class DetailViewModel (private val banjirRepository: BanjirRepository) : ViewModel() {
    private lateinit var banjirId: String

    fun setBanjir(banjirId: String) {
        this.banjirId = banjirId
    }

}
