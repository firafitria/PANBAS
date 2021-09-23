package com.dicoding.panbas.ui.form

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.dicoding.panbas.BuildConfig
import com.dicoding.panbas.R
import com.dicoding.panbas.data.datasource.local.entity.ReportEntity
import com.dicoding.panbas.data.datasource.remote.request.ReportRequest
import com.dicoding.panbas.data.source.StatusResponse
import com.dicoding.panbas.databinding.ActivityDetailBinding
import com.dicoding.panbas.databinding.ActivityFormBinding
import com.dicoding.panbas.ml.Model
import com.dicoding.panbas.ui.detail_report.DetailReportActivity
import com.dicoding.panbas.utils.ViewModelFactory
import org.tensorflow.lite.support.image.TensorImage
import java.io.File

class FormActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_IMG_URI = "extra_img_uri"
        const val EXTRA_FILE = "extra_file"
    }
    private lateinit var file: File

    private lateinit var viewModel: FormViewModel
    private lateinit var binding: ActivityFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[FormViewModel::class.java]

        val imageUri: Uri? = intent.getParcelableExtra(EXTRA_IMG_URI)
        file = intent.getSerializableExtra(EXTRA_FILE) as File
        imageUri?.path?.let { Log.d("FormActivity", it) }

        if (imageUri != null) {
            Glide.with(this).load(imageUri).into(binding.ivUpload)
        }
        binding.buttonBuat.setOnClickListener {
            kirimForm()
        }
    }

    private fun kirimForm() {
        binding.apply{
            val request = ReportRequest(
                file,
                textInputLokasi.text.toString(),
                textInputKeterangan.text.toString(),

            )
            viewModel.createReport(request).observe(this@FormActivity, {
                when(it.status) {
                    StatusResponse.SUCCESS -> {
                        Toast.makeText(this@FormActivity, "Sukses mengirim laporan", Toast.LENGTH_SHORT).show()
                        val laporan = it.body.laporan
                        if (laporan != null) {
                            val entity = ReportEntity(
                                laporan.id,
                                laporan.lokasi,
                                laporan.keterangan,
                                laporan.image)
                            startActivity(Intent(this@FormActivity,DetailReportActivity::class.java).apply {
                                putExtra(DetailReportActivity.REPORT, entity)
                            })
                        }
                        finish()
                    }
                    StatusResponse.ERROR -> {
                        Toast.makeText(this@FormActivity, "Gagal mengirim laporan: ${it.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
    }


}