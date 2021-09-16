package com.dicoding.panbas.data.repository.report

import com.dicoding.panbas.data.datasource.local.entity.BanjirEntity
import com.dicoding.panbas.data.datasource.local.entity.ReportEntity
import com.dicoding.panbas.data.datasource.remote.RemoteDataSource
import com.dicoding.panbas.data.datasource.remote.ReportDataSource

class ReportRepository private constructor(private val remoteDataSource: RemoteDataSource) :
    ReportDataSource {
    companion object {
        @Volatile
        private var instance: ReportRepository? = null
        fun getInstance(remoteData: RemoteDataSource): ReportRepository =
            instance ?: synchronized(this) {
                instance ?: ReportRepository(remoteData).apply { instance = this }
            }
    }

    override fun getAllReport(): ArrayList<ReportEntity> {
        val reportResponse = remoteDataSource.getAllReport()
        val reportList = ArrayList<ReportEntity>()
        for (response in reportResponse) {
            val report = ReportEntity(response.idreport,
                response.name,
                response.time,
                response.location,
                response.info,
                response.imagePath)
            reportList.add(report)
        }
        return reportList
    }

    override fun getItemReport(idreport: String): ReportEntity {
        val reportResponse = remoteDataSource.getItemReport(idreport)

        return ReportEntity(
            reportResponse.idreport,
            reportResponse.name,
            reportResponse.time,
            reportResponse.location,
            reportResponse.info,
            reportResponse.imagePath
        )
    }

}