package com.dicoding.panbas.ui.detail_report

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.panbas.R

class DetailReportActivity : AppCompatActivity() {
    companion object{
        const val BANJIR = "banjir"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_report)
    }
}