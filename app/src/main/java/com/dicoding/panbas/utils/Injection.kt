package com.dicoding.panbas.utils

import android.content.Context
import com.dicoding.panbas.data.datasource.remote.RemoteDataSource
import com.dicoding.panbas.data.repository.banjir.BanjirRepository
import com.dicoding.panbas.data.repository.report.ReportRepository

object Injection {
    fun provideBanjirRepository(context: Context): BanjirRepository {

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))

        return BanjirRepository.getInstance(remoteDataSource)
    }
    fun provideReportRepository(context: Context): ReportRepository {

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))

        return ReportRepository.getInstance(remoteDataSource)
    }

}