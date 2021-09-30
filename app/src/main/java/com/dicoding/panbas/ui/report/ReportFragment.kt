package com.dicoding.panbas.ui.report

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.panbas.data.Status
import com.dicoding.panbas.databinding.FragmentHomeBinding
import com.dicoding.panbas.databinding.FragmentReportBinding
import com.dicoding.panbas.ui.home.BanjirAdapter
import com.dicoding.panbas.ui.home.HomeViewModel
import com.dicoding.panbas.utils.ViewModelFactory

class ReportFragment : Fragment() {

    private lateinit var binding: FragmentReportBinding
    private lateinit var viewModel: ReportViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReportBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    companion object {
        @Volatile
        private var instance: ReportFragment? = null

        @JvmStatic
        fun getInstance(): ReportFragment = instance ?: synchronized(this) {
            instance ?: ReportFragment().apply {
                instance = this
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(requireActivity(), factory)[ReportViewModel::class.java]
//            val report = viewModel.getAllReport()
//
//            val reportAdapter = ReportAdapter()
//            reportAdapter.setReport(report)
//
//            with(binding.rvReport) {
//                layoutManager = LinearLayoutManager(context)
//                setHasFixedSize(true)
//                adapter = reportAdapter
            }

        getReportData()
        }

    private fun getReportData() {
        viewModel.getAllReport().observe(viewLifecycleOwner, {
            val adapter = ReportAdapter()
            val layoutManager = GridLayoutManager(context, 1)
            binding.rvReport.adapter = adapter
            binding.rvReport.layoutManager = layoutManager
            when(it.status) {
                Status.SUCCESS -> {
                    if (it.data != null) {
                        Log.d("ReportFragment", "Jumlah Data: ${it.data.size}")
                        Log.d("ReportFragment", "Data: ${it.data}")
                        adapter.setReport(it.data)
                    }
                }
                Status.LOADING -> {
                }
                Status.ERROR -> {
                    if (it.data != null) {
                        adapter.setReport(it.data)
                    }
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}
