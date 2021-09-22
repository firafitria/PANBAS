package com.dicoding.panbas.data.repository.banjir

import com.dicoding.panbas.data.datasource.local.entity.BanjirEntity

interface BanjirDataSource {
    fun getAllBanjir() : List<BanjirEntity>
    fun getItemBanjir(idbanjir : String): BanjirEntity
}