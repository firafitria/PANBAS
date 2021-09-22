package com.dicoding.panbas.ui.detail_report

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.LiveData
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
    private var detailReport: ReportEntity? = null
    private lateinit var mutableDetailReport: LiveData<ReportEntity>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetailReportBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onStart(){
        super.onStart()
        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailReportViewModel::class.java]
        viewModel.setReport(intent.getParcelableExtra(REPORT))

        showDetailReport()

    }

    private fun showDetailReport() {
        viewModel.getReport().observe(this, {
            binding.tvLokasi.text = it.lokasi
            binding.tvKet.text = it.keterangan
            Glide.with(this@DetailReportActivity)
                .load(it.linkFoto)
                .into(binding.ivReport)

        })

    }

}