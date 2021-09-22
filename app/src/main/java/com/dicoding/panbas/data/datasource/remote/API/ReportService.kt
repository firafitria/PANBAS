package com.dicoding.panbas.data.datasource.remote.API

import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface ReportService {
    @GET("laporan/index")
    fun getAllReport(): Call<ReportResponse>

    @Multipart
    @POST("laporan/store")
    fun createReport(
        @Part filePart: MultipartBody.Part,
        @Query ("lokasi") lokasi:String,
        @Query("keterangan") keterangan: String,
        @Query ("image") image: String
    ): Call <ReportResponse>
}