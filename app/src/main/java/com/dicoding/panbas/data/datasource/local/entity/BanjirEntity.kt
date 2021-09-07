package com.dicoding.panbas.data.datasource.local.entity

data class BanjirEntity (
    var idbanjir: String,
    var location: String,
    var city: String,
    var condition: Boolean = false,
    var imagePath: String
    )