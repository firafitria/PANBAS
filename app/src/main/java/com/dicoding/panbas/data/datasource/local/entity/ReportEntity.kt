package com.dicoding.panbas.data.datasource.local.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReportEntity (
    var idreport: String,
    var name: String,
    var time: String,
    var location: String,
    var info: String,
    var imagePath: String
    ) : Parcelable