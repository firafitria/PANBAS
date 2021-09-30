package com.dicoding.panbas.data.datasource.remote.request

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.panbas.data.datasource.remote.API.CreateResponse
import com.dicoding.panbas.data.datasource.remote.API.LaporanItem
import com.dicoding.panbas.data.datasource.remote.API.ReportResponse
import com.dicoding.panbas.data.datasource.remote.API.ReportService
import com.dicoding.panbas.data.datasource.response.ApiResponse
import com.dicoding.panbas.utils.DummyData
import com.dicoding.panbas.utils.MultipartHelper
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ReportRemoteDataSource private constructor(private val service: ReportService) {
    companion object {
        @Volatile
        private var instance: ReportRemoteDataSource? = null

        private const val TAG = "ReportRemoteDataSource"

        fun getInstance(service: ReportService): ReportRemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: ReportRemoteDataSource(service).apply { instance = this }
            }
    }

    fun getAllReport(): LiveData<ApiResponse<List<LaporanItem>>> {
        val mutableListReport = MutableLiveData<ApiResponse<List<LaporanItem>>>()
        service.getAllReport().enqueue(object : Callback<ReportResponse> {
            override fun onResponse(
                call: Call<ReportResponse>,
                response: Response<ReportResponse>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data != null) {
                        if (data.laporan != null && data.laporan.isNotEmpty()) {
                            val listReport = ApiResponse.success(data.laporan)
                            mutableListReport.postValue(listReport)
                        } else {
                            val result = ApiResponse.empty(response.message(), DummyData.listReport)
                            mutableListReport.postValue(result)
                        }
                    }
                    Log.e("ReportRemoteDataSource", "success: ${response.body()}")
                } else {
                    mutableListReport.postValue(
                        ApiResponse.error(
                            response.message(),
                            DummyData.listReport
                        )
                    )
                    Log.e("ReportRemoteDataSource", "onError: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ReportResponse>, t: Throwable) {
                Log.e("GetReport", "onFailure: ${t.message}")
                mutableListReport.postValue(ApiResponse.error(t.message, DummyData.listReport))
            }

        })
        return mutableListReport
    }

    fun createReport(request: ReportRequest): LiveData<ApiResponse<CreateResponse>> {

        val liveDataResponse = MutableLiveData<ApiResponse<CreateResponse>>()
//        val image = MultipartHelper.getPart(request.file)
//        service.createReport(lokasi = request.lokasi, keterangan = request.keterangan,
//            image = image).enqueue(object : Callback<CreateResponse> {
//            override fun onResponse(call: Call<CreateResponse>, response: Response<CreateResponse>) {
//                if (response.isSuccessful) {
//                    val data = response.body()
//                    if (data != null) {
//                        liveDataResponse.postValue(ApiResponse.success(data))
//                    }
//                    Log.d(TAG, "onResponse: ${response.body()}")
//                }else{
//                    liveDataResponse.postValue(ApiResponse.error(response.message(), CreateResponse()))
//                    Log.e("ReportRemoteDataSource", response.message())
//                }
//            }
//
//            override fun onFailure(call: Call<CreateResponse>, t: Throwable) {
//                t.message?.let {
//                    Log.e("ReportRemoteDataSource", it)
//                    liveDataResponse.postValue(ApiResponse.error(it, CreateResponse()))
//                }
//            }
//
//        })

        val rPhoneNumber: RequestBody =
            RequestBody.create("text/plain".toMediaTypeOrNull(), request.lokasi)
        val rPassword: RequestBody =
            RequestBody.create("text/plain".toMediaTypeOrNull(), request.keterangan)
        val rProfilePicture: MultipartBody.Part = MultipartHelper.getPart(request.file)
//        val builder: Retrofit.Builder =
//            Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(baseUrl)
//                .client(cookie.build())
//        val retrofit = builder.build()
//        val requestHandler: RequestHandler = retrofit.create(RequestHandler::class.java)
//        rProfilePicture = createFormData.createFormData(
//            "file",
//            file.getName(),
//            create(MediaType.parse("image/*"), file)
//        ) //sample image file that you want to upload

//        val call: Call<ServerMessage> //ServerMessage is a class with a String to store and convert json response

//        call = requestHandler.editProfile(
//            rPhoneNumber,
//            rPassword,
//            rProfilePicture
//        ) //editProfile is in RequestHandler interface

        service.createReport(rProfilePicture, rPhoneNumber, rPassword)
            .enqueue(object : Callback<CreateResponse> {
                override fun onResponse(
                    call: Call<CreateResponse>,
                    response: Response<CreateResponse>
                ) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        if (data != null) {
                            liveDataResponse.postValue(ApiResponse.success(data))
                        }
                        Log.d(TAG, "onResponse: ${response.body()}")
                    } else {
                        liveDataResponse.postValue(
                            ApiResponse.error(
                                response.message(),
                                CreateResponse()
                            )
                        )
                        Log.e("ReportRemoteDataSource", response.message())
                    }
                }

                override fun onFailure(call: Call<CreateResponse>, t: Throwable) {
                    t.message?.let {
                        Log.e("ReportRemoteDataSource", it)
                        liveDataResponse.postValue(ApiResponse.error(it, CreateResponse()))
                    }
                    t.printStackTrace()
                }
            })

//        call.enqueue(object : Callback<ServerMessage?> {
//            override fun onResponse(
//                call2: Call<ServerMessage?>,
//                response: Response<ServerMessage?>
//            ) {
//                //your code here
//            }
//
//            override fun onFailure(call: Call<ServerMessage?>, t: Throwable) {
//                //your code here
//            }
//        })
        return liveDataResponse
    }

}