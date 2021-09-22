package com.dicoding.panbas.data.datasource.remote.API

import com.google.gson.annotations.SerializedName

data class ReportResponse(

	@field:SerializedName("laporan")
	val laporan: List<LaporanItem>
)

data class LaporanItem(

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("keterangan")
	val keterangan: String,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("user_id")
	val userId: Any,

	@field:SerializedName("lokasi")
	val lokasi: String,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("id")
	val id: Int
)
