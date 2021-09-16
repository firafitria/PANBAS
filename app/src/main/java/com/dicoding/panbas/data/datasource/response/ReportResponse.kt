package com.dicoding.panbas.data.datasource.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReportResponse (
    var idreport: String,
    var name: String,
    var time:String,
    var location: String,
    var info:String,
    var imagePath: String
        ): Parcelable