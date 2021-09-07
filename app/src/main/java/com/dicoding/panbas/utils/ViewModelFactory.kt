package com.dicoding.panbas.utils

import android.content.Context
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory private constructor (
) : ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: ViewModelFactory(
            )
        }
    }
}