package com.dicoding.panbas.data.datasource.remote

import com.dicoding.panbas.data.datasource.response.BanjirResponse
import com.dicoding.panbas.utils.JsonHelper

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(helper).apply { instance = this }
            }
    }

    fun getAllBanjir(): List<BanjirResponse> = jsonHelper.loadBanjir()

    fun getItemBanjir(idbanjir: String): BanjirResponse = jsonHelper.loadItemBanjir(idbanjir)



}