package com.dicoding.panbas.ui.rawan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.panbas.R
import com.dicoding.panbas.data.datasource.local.entity.ListEntity
import com.dicoding.panbas.databinding.ActivityRawanBinding
import com.dicoding.panbas.ui.home.HomeViewModel
import com.dicoding.panbas.utils.ViewModelFactory

class RawanActivity : AppCompatActivity() {
    private lateinit var binding:ActivityRawanBinding
    private var list:ArrayList<ListEntity> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRawanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvRawan.setHasFixedSize(true)

        list.addAll(RawansData.listData)
        showRecyclerList()
    }

    private fun showRecyclerList() {
        binding.rvRawan.layoutManager = LinearLayoutManager(this)
        val listRawanAdapter = RawanAdapter(list)
        binding.rvRawan.adapter = listRawanAdapter
    }
}