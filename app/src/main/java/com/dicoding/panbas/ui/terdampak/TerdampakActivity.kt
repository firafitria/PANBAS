package com.dicoding.panbas.ui.terdampak

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.panbas.R
import com.dicoding.panbas.data.datasource.local.entity.ListEntity
import com.dicoding.panbas.databinding.ActivityRawanBinding
import com.dicoding.panbas.databinding.ActivityTerdampakBinding
import com.dicoding.panbas.ui.rawan.RawanAdapter
import com.dicoding.panbas.ui.rawan.RawansData

class TerdampakActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTerdampakBinding
    private var list:ArrayList<ListEntity> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityTerdampakBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvTerdampak.setHasFixedSize(true)

        list.addAll(TerdampaksData.listData)
        showRecyclerList()
    }

    private fun showRecyclerList() {
        binding.rvTerdampak.layoutManager = LinearLayoutManager(this)
        val list = TerdampakAdapter(list)
        binding.rvTerdampak.adapter = list
    }
}