package com.dicoding.panbas.utils

import android.content.Context
import com.dicoding.panbas.data.datasource.local.ReportLocalDataSource
import com.dicoding.panbas.data.datasource.local.database.PanbasDatabase
import com.dicoding.panbas.data.datasource.remote.API.PanbasService
import com.dicoding.panbas.data.datasource.remote.RemoteDataSource
import com.dicoding.panbas.data.datasource.remote.request.ReportRemoteDataSource
import com.dicoding.panbas.data.repository.banjir.BanjirRepository
import com.dicoding.panbas.data.repository.report.ReportRepository

object Injection {
    fun provideBanjirRepository(context: Context): BanjirRepository {

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))

        return BanjirRepository.getInstance(remoteDataSource)
    }
    fun provideReportRepository(context: Context): ReportRepository {
        val database = PanbasDatabase.getInstance(context)
        val localDataSource = ReportLocalDataSource.getInstance(database.reportDao())

        val panbasService= PanbasService()
        val appExecutor = AppExecutor()
        val remote = ReportRemoteDataSource.getInstance(panbasService.getReportService())

        return ReportRepository.getInstance(remote,localDataSource,appExecutor)
    }

}