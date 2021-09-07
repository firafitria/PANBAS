package com.dicoding.panbas.data.repository.banjir

import com.dicoding.panbas.data.datasource.local.entity.BanjirEntity
import com.dicoding.panbas.data.datasource.remote.BanjirDataSource
import com.dicoding.panbas.data.datasource.remote.RemoteDataSource

class BanjirRepository private constructor(private val remoteDataSource: RemoteDataSource) : BanjirDataSource {

    companion object {
        @Volatile
        private var instance: BanjirRepository? = null
        fun getInstance(remoteData: RemoteDataSource): BanjirRepository =
            instance ?: synchronized(this) {
                instance ?: BanjirRepository(remoteData).apply { instance = this }
            }
    }

    override fun getAllBanjir(): ArrayList<BanjirEntity> {
        val banjirResponse = remoteDataSource.getAllBanjir()
        val banjirList = ArrayList<BanjirEntity>()
        for (response in banjirResponse) {
            val banjir = BanjirEntity(response.idbanjir,
                response.location,
                response.city,
                false,
                response.imagePath)
            banjirList.add(banjir)
        }
        return banjirList
    }
}