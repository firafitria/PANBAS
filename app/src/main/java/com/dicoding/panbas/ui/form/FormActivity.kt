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
import com.bumptech.glide.Glide
import com.dicoding.panbas.BuildConfig
import com.dicoding.panbas.R
import com.dicoding.panbas.databinding.ActivityDetailBinding
import com.dicoding.panbas.databinding.ActivityFormBinding
import com.dicoding.panbas.ml.Model
import org.tensorflow.lite.support.image.TensorImage
import java.io.File

class FormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFormBinding
    lateinit var bitmap: Bitmap
    private val AUTHORITY = BuildConfig.APPLICATION_ID + ".provider"
    private lateinit var imageUri : Uri //uri lokasi dari foto
    private lateinit var output : File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding){
            imageButton.setOnClickListener(View.OnClickListener {
                var camera : Intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                imageUri = FileProvider.getUriForFile(this@FormActivity, AUTHORITY, output)
                imageUri.path?.let { Log.d("Photos", it) }
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
                startActivityForResult(camera, 200) })
            button3.setOnClickListener(View.OnClickListener {
                    var resized = Bitmap.createScaledBitmap(bitmap, 224, 224, true)
                    val model = Model.newInstance(this@FormActivity)
                    var tbuffer = TensorImage.fromBitmap(resized)
                    var byteBuffer = tbuffer.buffer
// Creates inputs for reference.
                    val image = TensorImage.fromBitmap(bitmap)
// Runs model inference and gets result.
                    val outputs = model.process(image)
                    val probability = outputs.probabilityAsCategoryList
                     tvPrediksi.text = probability.toString()
// Releases model resources if no longer used.
                    model.close()
                })

            buttonBuat.setOnClickListener {
                kirimForm()
            }
        }
    }

    private fun kirimForm() {
        binding.apply{
            when{
                textInputLokasi.helperText?.isEmpty() == true -> {
                    textInputLokasi.error = "Kolom harus diisi"
                    textInputLokasi.requestFocus()
                    return
                }
                textInputKeterangan.helperText?.isEmpty() == true -> {
                    textInputKeterangan.error = "Kolom harus diisi"
                    textInputKeterangan.requestFocus()
                    return
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 100){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "Camera permission granted", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun checkandGetpermissions(){
        if(checkSelfPermission(android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED){
            requestPermissions(arrayOf(android.Manifest.permission.CAMERA), 100)
        }
        else{
            Toast.makeText(this, "Camera permission granted", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 250){
            binding.ivUpload.setImageURI(data?.data)

            var uri : Uri?= data?.data
            bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, uri)
        }
        else if(requestCode == 200 && resultCode == Activity.RESULT_OK){
            bitmap = data?.extras?.get("data") as Bitmap
            binding.ivUpload.setImageBitmap(bitmap)
        }

    }

}