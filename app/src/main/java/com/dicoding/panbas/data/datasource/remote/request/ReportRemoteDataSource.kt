package com.dicoding.panbas.data.datasource.remote.request

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.panbas.data.datasource.remote.API.LaporanItem
import com.dicoding.panbas.data.datasource.remote.API.ReportResponse
import com.dicoding.panbas.data.datasource.remote.API.ReportService
import com.dicoding.panbas.data.datasource.response.ApiResponse
import com.dicoding.panbas.utils.DummyData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReportRemoteDataSource private constructor(private val service: ReportService) {
    companion object {
        @Volatile
        private var instance: ReportRemoteDataSource? = null

        fun getInstance(service: ReportService): ReportRemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: ReportRemoteDataSource(service).apply { instance = this }
            }
    }
    fun getAllReport(): LiveData<ApiResponse<List<LaporanItem>>> {
        val mutableListReport = MutableLiveData<ApiResponse<List<LaporanItem>>>()
        service.getAllReport().enqueue(object : Callback<ReportResponse> {
            override fun onResponse(
                call: Call<ReportResponse>,
                response: Response<ReportResponse>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data != null) {
                        if (data.laporan != null && data.laporan.isNotEmpty()) {
                            val listReport = ApiResponse.success(data.laporan)
                            mutableListReport.postValue(listReport)
                        }else{
                            val result = ApiResponse.empty(response.message(), DummyData.listReport)
                            mutableListReport.postValue(result)
                        }
                    }
                }else{
                    mutableListReport.postValue(ApiResponse.error(response.message(), DummyData.listReport))
                    Log.e("GetReport", "onError: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ReportResponse>, t: Throwable) {
                Log.e("GetReport", "onFailure: ${t.message}")
                mutableListReport.postValue(ApiResponse.error(t.message, DummyData.listReport))
            }

        })
        return mutableListReport
    }

}