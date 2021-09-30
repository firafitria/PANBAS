package com.dicoding.panbas.ui.lapor

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.core.content.FileProvider
import androidx.viewbinding.BuildConfig
import com.dicoding.panbas.databinding.ActivityLaporBinding
import com.dicoding.panbas.ui.camera.CameraActivity
import com.dicoding.panbas.ui.form.FormActivity
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random

class LaporActivity : AppCompatActivity() {

    private lateinit var binding:ActivityLaporBinding
    private val AUTHORITY = BuildConfig.LIBRARY_PACKAGE_NAME + ".provider"
    private lateinit var imageUri : Uri //uri lokasi dari foto
    private lateinit var output : File

    companion object {
        private const val TAKE_PICTURE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLaporBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button4.setOnClickListener {
            lapor()
        }

        binding.button5.setOnClickListener {
            val pindah= Intent(this, CameraActivity::class.java)

            startActivity(pindah)
        }

    }

    private fun lapor() {
        val randNumber = Random(30).nextInt(100)
        val date = SimpleDateFormat("yyyymmdhhmmss", Locale.getDefault()).format(Date())
        val fileName = "$date-$randNumber.jpg"

        output = File(File(filesDir, "photos"), fileName)

        if (output.exists()) output.delete() else output.parentFile.mkdirs()

        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        imageUri = FileProvider.getUriForFile(this, AUTHORITY, output)
        imageUri.path?.let { Log.d("Photos", it) }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
        startActivityForResult(intent, TAKE_PICTURE)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == TAKE_PICTURE) {
            if(resultCode == RESULT_OK) {
                contentResolver.notifyChange(imageUri, null)
                val intent = Intent(this, FormActivity::class.java)
                intent.putExtra(FormActivity.EXTRA_IMG_URI, imageUri)
                intent.putExtra(FormActivity.EXTRA_FILE, output)
                startActivity(intent)
            }
        }
    }
}