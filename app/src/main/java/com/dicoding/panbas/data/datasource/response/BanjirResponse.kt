package com.dicoding.panbas.data.datasource.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BanjirResponse(
        var idbanjir: String,
        var location: String,
        var city: String,
        var condition: Boolean,
        var imagePath: String
        ): Parcelable