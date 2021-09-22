package com.dicoding.panbas.data.datasource.remote.API

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PanbasService {

    private val client = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()

    private val retrofit = Retrofit.Builder()
                .baseUrl("https://panbas.deka.dev/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
    fun getReportService(): ReportService = retrofit.create(ReportService::class.java)

    }
