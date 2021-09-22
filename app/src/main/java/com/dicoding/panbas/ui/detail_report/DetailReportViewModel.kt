package com.dicoding.panbas.ui.detail_report

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.panbas.data.datasource.local.entity.BanjirEntity
import com.dicoding.panbas.data.datasource.local.entity.ReportEntity
import com.dicoding.panbas.data.repository.report.ReportRepository

class DetailReportViewModel(private val reportRepository: ReportRepository) : ViewModel() {
    private val mutableReport = MutableLiveData<ReportEntity>()

    fun getReport(): LiveData<ReportEntity> = mutableReport
    fun setReport(reportEntity: ReportEntity?) {mutableReport.postValue(reportEntity)}
}