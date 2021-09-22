package com.dicoding.panbas.data.repository.report

import androidx.lifecycle.LiveData
import com.dicoding.panbas.data.datasource.local.entity.ReportEntity
import com.dicoding.panbas.data.vo.Resource

interface IReportRepository {
    fun getAllReport() : LiveData<Resource<List<ReportEntity>>>
}