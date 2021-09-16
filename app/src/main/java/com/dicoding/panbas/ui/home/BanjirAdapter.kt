package com.dicoding.panbas.ui.home

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.panbas.data.datasource.local.entity.BanjirEntity
import com.dicoding.panbas.databinding.ItemBanjirBinding
import com.dicoding.panbas.ui.detail.DetailActivity
import com.dicoding.panbas.ui.detail.DetailActivity.Companion.BANJIR

class BanjirAdapter  : RecyclerView.Adapter<BanjirAdapter.BanjirViewHolder>() {

    private val listBanjir = ArrayList<BanjirEntity>()

    fun setData(data: List<BanjirEntity>) {
        listBanjir.clear()
        listBanjir.addAll(data)
        notifyDataSetChanged()

    }
    inner class BanjirViewHolder (private val binding: ItemBanjirBinding):
    RecyclerView.ViewHolder(binding.root){

        fun bind(banjir: BanjirEntity) {
            with(binding) {
                tvCity.text = banjir.location
                Glide.with(binding.root).load(banjir.imagePath)
                    .into(binding.ivBanjir)


                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java).putExtra(
                        BANJIR, banjir
                    )
                    it.context.startActivity(intent)
                    Log.d("ADAPTER", "bind: ${banjir.idbanjir}")
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BanjirViewHolder {
        val binding = ItemBanjirBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BanjirViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BanjirViewHolder, position: Int) {
        holder.bind(listBanjir[position])
    }

    override fun getItemCount()= listBanjir.size
}