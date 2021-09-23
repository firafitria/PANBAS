package com.dicoding.panbas.data.datasource.remote.request

import java.io.File

data class ReportRequest (
    var file: File,
    var lokasi:String,
    var keterangan: String,
        )