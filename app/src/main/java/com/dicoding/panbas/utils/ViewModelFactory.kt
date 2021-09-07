package com.dicoding.panbas.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.panbas.data.repository.banjir.BanjirRepository
import com.dicoding.panbas.ui.home.HomeViewModel

class ViewModelFactory private constructor (private val banjirRepository: BanjirRepository): ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: ViewModelFactory(Injection.provideRepository(context)).apply {
                instance = this
            }
        }
    }
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(banjirRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: ")
        }
    }
}