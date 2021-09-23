package com.dicoding.panbas.data.repository.report

import androidx.lifecycle.LiveData
import com.dicoding.panbas.data.datasource.local.entity.ReportEntity
import com.dicoding.panbas.data.datasource.remote.API.CreateResponse
import com.dicoding.panbas.data.datasource.remote.API.ReportResponse
import com.dicoding.panbas.data.datasource.remote.request.ReportRequest
import com.dicoding.panbas.data.datasource.response.ApiResponse
import com.dicoding.panbas.data.vo.Resource

interface IReportRepository {
    fun getAllReport() : LiveData<Resource<List<ReportEntity>>>

    fun createReport(request: ReportRequest): LiveData<ApiResponse<CreateResponse>>
}