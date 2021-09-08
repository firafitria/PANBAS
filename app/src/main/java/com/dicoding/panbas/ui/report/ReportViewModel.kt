package com.dicoding.panbas.ui.report

import androidx.lifecycle.ViewModel
import com.dicoding.panbas.data.repository.banjir.BanjirRepository
import com.dicoding.panbas.data.repository.report.ReportRepository

class ReportViewModel (
    private val reportRepository: ReportRepository
): ViewModel(){
    fun getAllReport() = reportRepository.getAllReport()
}