package com.dicoding.panbas.data.datasource.remote

import com.dicoding.panbas.data.datasource.local.entity.BanjirEntity
import com.dicoding.panbas.data.datasource.local.entity.ReportEntity

interface ReportDataSource {
    fun getAllReport() : List<ReportEntity>
}