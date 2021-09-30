package com.dicoding.panbas.ui.report

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.panbas.data.datasource.local.entity.ReportEntity
import com.dicoding.panbas.data.repository.banjir.BanjirRepository
import com.dicoding.panbas.data.repository.report.ReportRepository
import com.dicoding.panbas.data.vo.Resource

class ReportViewModel (
    private val reportRepository: ReportRepository
): ViewModel(){
    fun getAllReport(): LiveData<Resource<List<ReportEntity>>> {
        val data = reportRepository.getAllReport()
        Log.d("model", "getAllReport: $data")
        return data
    }
}