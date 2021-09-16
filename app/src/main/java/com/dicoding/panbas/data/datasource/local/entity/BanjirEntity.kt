package com.dicoding.panbas.data.datasource.local.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BanjirEntity (
    var idbanjir: String,
    var location: String,
    var city: String,
    var condition: String,
    var imagePath: String
    ): Parcelable