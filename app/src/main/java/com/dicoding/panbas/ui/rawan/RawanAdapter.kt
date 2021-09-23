package com.dicoding.panbas.ui.rawan

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.panbas.data.datasource.local.entity.ListEntity
import com.dicoding.panbas.databinding.ItemListBinding

class RawanAdapter(private val listRawan: ArrayList<ListEntity>) : RecyclerView.Adapter<RawanAdapter.RawanViewHolder>() {


    inner class RawanViewHolder (private val binding: ItemListBinding):
    RecyclerView.ViewHolder(binding.root){
        fun bind(rawan: ListEntity){
            binding.tvLokasiCard.text = rawan.location
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RawanViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RawanViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RawanViewHolder, position: Int) {
        holder.bind(listRawan[position])
    }

    override fun getItemCount()=listRawan.size

}