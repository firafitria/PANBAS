package com.dicoding.panbas.data.datasource.remote

import com.dicoding.panbas.data.datasource.local.entity.BanjirEntity

interface BanjirDataSource {
    fun getAllBanjir() : List<BanjirEntity>
    fun getItemBanjir(idbanjir : String): BanjirEntity
}