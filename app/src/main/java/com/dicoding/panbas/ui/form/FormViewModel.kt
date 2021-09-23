package com.dicoding.panbas.ui.form

import androidx.lifecycle.ViewModel
import com.dicoding.panbas.data.datasource.remote.request.ReportRequest
import com.dicoding.panbas.data.repository.report.IReportRepository
import com.dicoding.panbas.data.repository.report.ReportRepository

class FormViewModel(private val report: IReportRepository): ViewModel (){
    fun createReport(request: ReportRequest) = report.createReport(request)

}