package com.dicoding.panbas.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dicoding.panbas.R
import com.dicoding.panbas.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var binding:FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    companion object {
        const val REQUEST_CHECK_SETTINGS = 10

        @Volatile
        private var instance: HomeFragment? = null

        @JvmStatic
        fun getInstance(): HomeFragment = instance ?: synchronized(this) {
            instance ?: HomeFragment().apply {
                instance = this
            }
        }
    }
}