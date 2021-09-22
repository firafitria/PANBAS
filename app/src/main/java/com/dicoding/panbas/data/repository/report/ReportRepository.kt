package com.dicoding.panbas.data.repository.report

import androidx.lifecycle.LiveData
import com.dicoding.panbas.data.NetworkBoundResource
import com.dicoding.panbas.data.datasource.local.ReportLocalDataSource
import com.dicoding.panbas.data.datasource.local.entity.ReportEntity
import com.dicoding.panbas.data.datasource.remote.API.LaporanItem
import com.dicoding.panbas.data.datasource.remote.request.ReportRemoteDataSource
import com.dicoding.panbas.data.datasource.response.ApiResponse
import com.dicoding.panbas.data.vo.Resource
import com.dicoding.panbas.utils.AppExecutor
import com.dicoding.panbas.utils.DummyData.listReport

class ReportRepository private constructor(
    private val remote: ReportRemoteDataSource,
    private val local: ReportLocalDataSource,
    private val appExecutor: AppExecutor) :
    IReportRepository {
    companion object {
        @Volatile
        private var instance: ReportRepository? = null
        fun getInstance(
            remote: ReportRemoteDataSource,
            local: ReportLocalDataSource,
            appExecutor: AppExecutor
        ): ReportRepository =
            instance ?: synchronized(this) {
                instance ?: ReportRepository(remote,local, appExecutor).apply { instance = this }
            }
    }


    override fun getAllReport() : LiveData<Resource<List<ReportEntity>>> {

        return object : NetworkBoundResource<List<ReportEntity>, List<LaporanItem>>(appExecutor){

            override fun shouldFetch(data: List<ReportEntity>?): Boolean {
                return true
            }
            override fun createCall(): LiveData<ApiResponse<List<LaporanItem>>> {
                return remote.getAllReport()
            }
            override fun saveCallResult(data: List<LaporanItem>) {
                val listReport = data.map { item ->
                    ReportEntity(
                        id = item.id,
                        lokasi = item.lokasi,
                        keterangan = item.keterangan,
                        linkFoto = item.image,
                    )
                }
                local.insertReport(listReport)
            }

            override fun loadFromDB(): LiveData<List<ReportEntity>> {
                return local.getAllReport()
            }
        }.asLiveData()

    }

}