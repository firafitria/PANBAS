package com.dicoding.panbas.ui.main

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.dicoding.panbas.BuildConfig
import com.dicoding.panbas.R
import com.dicoding.panbas.databinding.ActivityMainBinding
import com.dicoding.panbas.ui.form.FormActivity
import com.dicoding.panbas.ui.home.HomeFragment
import com.dicoding.panbas.ui.lapor.LaporActivity
import com.dicoding.panbas.ui.report.ReportFragment
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private val AUTHORITY = BuildConfig.APPLICATION_ID + ".provider"
    private lateinit var imageUri : Uri //uri lokasi dari foto
    private lateinit var output : File

    companion object {
        private const val TAKE_PICTURE = 100
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
    override fun onStart() {
        super.onStart()

        val homeFragment = HomeFragment.getInstance()
        val reportFragment = ReportFragment.getInstance()
        moveFragment(homeFragment)
        binding.btnReport.setOnClickListener {
            lapor()
//            val pindah= Intent(this, LaporActivity::class.java)
//
//            startActivity(pindah)

        }
        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> moveFragment(homeFragment)
                R.id.nav_profile -> moveFragment(reportFragment)
            }
            true
        }
    }

    private fun moveFragment(fragment: Fragment) = supportFragmentManager.beginTransaction().apply{
        replace(R.id.wrapper, fragment)
        commit()
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
            if(resultCode == Activity.RESULT_OK) {
                contentResolver.notifyChange(imageUri, null)
                val intent = Intent(this, FormActivity::class.java)
                intent.putExtra(FormActivity.EXTRA_IMG_URI, imageUri)
                intent.putExtra(FormActivity.EXTRA_FILE, output)
                startActivity(intent)
            }
        }
    }

}