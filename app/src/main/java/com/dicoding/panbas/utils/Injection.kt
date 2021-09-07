package com.dicoding.panbas.utils

import android.content.Context
import com.dicoding.panbas.data.datasource.remote.RemoteDataSource
import com.dicoding.panbas.data.repository.banjir.BanjirRepository

object Injection {
    fun provideRepository(context: Context): BanjirRepository {

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))

        return BanjirRepository.getInstance(remoteDataSource)
    }
}