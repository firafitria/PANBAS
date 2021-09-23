package com.dicoding.panbas.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.panbas.data.repository.banjir.BanjirRepository
import com.dicoding.panbas.data.repository.report.ReportRepository
import com.dicoding.panbas.ui.detail.DetailViewModel
import com.dicoding.panbas.ui.detail_report.DetailReportViewModel
import com.dicoding.panbas.ui.form.FormViewModel
import com.dicoding.panbas.ui.home.HomeViewModel
import com.dicoding.panbas.ui.main.MainViewModel
import com.dicoding.panbas.ui.report.ReportViewModel

class ViewModelFactory private constructor (
    private val banjirRepository: BanjirRepository,
    private val reportRepository: ReportRepository ): ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: ViewModelFactory(
                Injection.provideBanjirRepository(context),
                Injection.provideReportRepository(context),
            )
            }
        }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(banjirRepository) as T
            }
            modelClass.isAssignableFrom(ReportViewModel::class.java) -> {
                ReportViewModel(reportRepository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(banjirRepository) as T
            }
            modelClass.isAssignableFrom(DetailReportViewModel::class.java)->{
                DetailReportViewModel(reportRepository) as T
            }
            modelClass.isAssignableFrom(FormViewModel::class.java)->{
                FormViewModel(reportRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: ")
        }
    }
}