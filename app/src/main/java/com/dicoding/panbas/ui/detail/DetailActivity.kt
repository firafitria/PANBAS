package com.dicoding.panbas.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.dicoding.panbas.R
import com.dicoding.panbas.data.datasource.local.entity.BanjirEntity
import com.dicoding.panbas.databinding.ActivityDetailBinding
import com.dicoding.panbas.utils.ViewModelFactory

class DetailActivity : AppCompatActivity() {

    companion object{
        const val BANJIR = "banjir"
    }

    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val banjir = extras.getParcelable<BanjirEntity>(BANJIR)
            if (banjir!= null) {
                viewModel.setBanjir(banjir.idbanjir)
                populateBanjir(viewModel.getItemBanjir())

            }
        }
    }

    private fun populateBanjir(banjir: BanjirEntity) {
        with(binding) {
            tvCity.text = banjir.location
            Glide.with(binding.root).load(banjir.imagePath)
                .into(binding.ivBanjir2)
            tvCondition.text = banjir.condition

    }

    }


}