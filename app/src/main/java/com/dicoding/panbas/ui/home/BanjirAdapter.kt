package com.dicoding.panbas.ui.home

import android.content.Intent
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.panbas.R
import com.dicoding.panbas.data.datasource.local.entity.BanjirEntity
import com.dicoding.panbas.databinding.ItemBanjirBinding
import com.dicoding.panbas.ui.detail_report.DetailReportActivity

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
                tvCity.text = banjir.city
                tvCity2.text = banjir.location
                Glide.with(binding.root).load(banjir.imagePath)
                    .into(binding.ivBanjir)


                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailReportActivity::class.java)
                    intent.putExtra(DetailReportActivity, banjir)
                    it.context.startActivity(intent)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BanjirViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: BanjirViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}