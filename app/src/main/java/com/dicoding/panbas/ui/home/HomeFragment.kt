package com.dicoding.panbas.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.panbas.R
import com.dicoding.panbas.databinding.FragmentHomeBinding
import com.dicoding.panbas.ui.profile.ProfileActivity
import com.dicoding.panbas.ui.rawan.RawanActivity
import com.dicoding.panbas.ui.terdampak.TerdampakActivity
import com.dicoding.panbas.utils.ViewModelFactory


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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(requireActivity(), factory)[HomeViewModel::class.java]
            val banjir = viewModel.getAllBanjir()

            val banjirAdapter = BanjirAdapter()
            banjirAdapter.setData(banjir)

            with(binding.rvBanjir) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = banjirAdapter
                }
        }

        binding.btnProfile.setOnClickListener {
            requireActivity().run {
                startActivity(Intent(this, ProfileActivity::class.java))
                finish()
            }
        }

        binding.cardRawan.setOnClickListener {
            requireActivity().run{
                startActivity(Intent(this, RawanActivity::class.java))
                finish()
            }
        }
        binding.cardTerdampak.setOnClickListener {
            requireActivity().run{
                startActivity(Intent(this, TerdampakActivity::class.java))
                finish()
            }
        }
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