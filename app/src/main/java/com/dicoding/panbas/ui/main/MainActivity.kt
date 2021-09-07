package com.dicoding.panbas.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dicoding.panbas.R
import com.dicoding.panbas.databinding.ActivityMainBinding
import com.dicoding.panbas.ui.home.HomeFragment
import com.dicoding.panbas.ui.report.ReportFragment
import com.dicoding.panbas.utils.ViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
    override fun onStart() {
        super.onStart()

        val homeFragment = HomeFragment.getInstance()
        val reportFragment = ReportFragment.getInstance()
        moveFragment(homeFragment)
        binding.btnReport.setOnClickListener {
            report()
        }
        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> moveFragment(homeFragment)
                R.id.nav_profile -> moveFragment(reportFragment)
            }
            true
        }
    }

    private fun moveFragment(fragment: Fragment) = supportFragmentManager.beginTransaction().apply{
        replace(R.id.wrapper, fragment)
        commit()
    }


    private fun report() {
        TODO("Not yet implemented")
    }
}