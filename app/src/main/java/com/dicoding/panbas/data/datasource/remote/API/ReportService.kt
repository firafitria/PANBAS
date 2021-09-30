package com.dicoding.panbas.data.datasource.remote.API

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*


interface ReportService {
    @GET("laporan/index")
    fun getAllReport(): Call<ReportResponse>

    @Multipart
    @POST("laporan/store")
    fun createReport(
        @Part image: MultipartBody.Part,
        @Part ("lokasi") lokasi:RequestBody,
        @Part("keterangan") keterangan: RequestBody,
    ): Call <CreateResponse>

}