package com.dicoding.panbas.ui.detail_report

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.dicoding.panbas.R
import com.dicoding.panbas.data.datasource.local.entity.BanjirEntity
import com.dicoding.panbas.data.datasource.local.entity.ReportEntity
import com.dicoding.panbas.databinding.ActivityDetailReportBinding
import com.dicoding.panbas.databinding.FragmentReportBinding
import com.dicoding.panbas.ui.detail.DetailActivity
import com.dicoding.panbas.utils.ViewModelFactory

class DetailReportActivity : AppCompatActivity() {

    companion object{
        const val REPORT = "report"
    }

    private lateinit var binding: ActivityDetailReportBinding
    private lateinit var viewModel: DetailReportViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetailReportBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[DetailReportViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val report = extras.getParcelable<ReportEntity>(REPORT)
            if (report!= null) {
                viewModel.setReport(report.idreport)
                populateReport(viewModel.getItemReport())

            }
        }
    }

    private fun populateReport(report: ReportEntity) {
        with(binding) {
            tvName.text = report.name
            tvWaktu.text = report.time
            Glide.with(binding.root).load(report.imagePath)
                .into(binding.ivReport)
            tvLokasi.text = report.location
            tvKet.text = report.info

        }

    }
}