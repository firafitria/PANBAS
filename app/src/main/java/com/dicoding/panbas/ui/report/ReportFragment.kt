package com.dicoding.panbas.ui.report

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dicoding.panbas.databinding.FragmentHomeBinding
import com.dicoding.panbas.databinding.FragmentReportBinding

class ReportFragment : Fragment() {

    private lateinit var binding: FragmentReportBinding

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
}