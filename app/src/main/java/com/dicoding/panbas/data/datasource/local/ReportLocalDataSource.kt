package com.dicoding.panbas.data.datasource.local

import com.dicoding.panbas.data.datasource.local.dao.ReportDao
import com.dicoding.panbas.data.datasource.local.entity.ReportEntity

class ReportLocalDataSource private constructor(private val reportDao: ReportDao) {

    companion object {
        @Volatile
        private var instance: ReportLocalDataSource? = null

        fun getInstance(reportDao: ReportDao):ReportLocalDataSource {
            return instance ?: synchronized(this) {
                instance ?: ReportLocalDataSource(reportDao).apply {
                    instance = this
                }
            }
        }
    }

    fun getAllReport() = reportDao.getAllReport()
    fun insertReport(list: List<ReportEntity>) = reportDao.insertReport(list)
}