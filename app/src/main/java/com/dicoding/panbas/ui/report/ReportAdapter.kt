package com.dicoding.panbas.ui.report

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.panbas.data.datasource.local.entity.ReportEntity
import com.dicoding.panbas.databinding.ItemLaporanBinding
import com.dicoding.panbas.ui.detail_report.DetailReportActivity

class ReportAdapter : RecyclerView.Adapter<ReportAdapter.ReportViewHolder>() {

    private val listReport = ArrayList<ReportEntity>()

    fun setReport(data: List<ReportEntity>) {
        listReport.clear()
        listReport.addAll(data)
        notifyDataSetChanged()

    }

    inner class ReportViewHolder(private val binding: ItemLaporanBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(report: ReportEntity) {
            with(binding) {
                tvCity3.text = report.location
                tvDesc.text = report.info
                Glide.with(binding.root).load(report.imagePath)
                    .into(binding.ivBanjir1)


                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailReportActivity::class.java)
                    it.context.startActivity(intent)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportViewHolder {
        val binding = ItemLaporanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReportViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReportViewHolder, position: Int) {
        holder.bind(listReport[position])
    }

    override fun getItemCount()= listReport.size
}