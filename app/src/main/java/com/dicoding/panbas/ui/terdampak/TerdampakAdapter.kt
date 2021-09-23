package com.dicoding.panbas.ui.terdampak

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.panbas.data.datasource.local.entity.ListEntity
import com.dicoding.panbas.databinding.ItemListBinding
import com.dicoding.panbas.ui.rawan.RawanAdapter

class TerdampakAdapter (private val listTerdampak: ArrayList<ListEntity>) :
    RecyclerView.Adapter<TerdampakAdapter.TerdampakViewHolder>() {

    inner class TerdampakViewHolder(private val binding: ItemListBinding):
    RecyclerView.ViewHolder(binding.root){
        fun bind(terdampak: ListEntity){
            binding.tvLokasiCard.text = terdampak.location
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TerdampakViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TerdampakViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TerdampakViewHolder, position: Int) {
        holder.bind(listTerdampak[position])
    }

    override fun getItemCount()=listTerdampak.size

}