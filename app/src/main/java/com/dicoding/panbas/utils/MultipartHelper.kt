package com.dicoding.panbas.utils

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

object MultipartHelper {
    fun getPart(file: File): MultipartBody.Part {
        return MultipartBody.Part.createFormData(
            "image",
            file.name,
            file.asRequestBody("image/*".toMediaTypeOrNull())
        )
    }
}