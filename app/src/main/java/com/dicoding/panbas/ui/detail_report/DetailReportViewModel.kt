package com.dicoding.panbas.ui.detail_report

import androidx.lifecycle.ViewModel
import com.dicoding.panbas.data.datasource.local.entity.BanjirEntity
import com.dicoding.panbas.data.datasource.local.entity.ReportEntity
import com.dicoding.panbas.data.repository.report.ReportRepository

class DetailReportViewModel(private val reportRepository: ReportRepository) : ViewModel() {
    private lateinit var idreport: String

    fun setReport(idreport: String) {
        this.idreport = idreport
    }
    fun getItemReport(): ReportEntity = reportRepository.getItemReport(idreport)
}