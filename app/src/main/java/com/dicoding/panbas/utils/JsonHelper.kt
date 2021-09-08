package com.dicoding.panbas.utils

import android.content.Context
import com.dicoding.panbas.data.datasource.response.BanjirResponse
import com.dicoding.panbas.data.datasource.response.ReportResponse
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException


class JsonHelper (private val context: Context) {
    private fun parsingFileToString(fileName: String): String? {
        return try {
            val `is` = context.assets.open(fileName)
            val buffer = ByteArray(`is`.available())
            `is`.read(buffer)
            `is`.close()
            String(buffer)

        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }

    fun loadBanjir(): List<BanjirResponse> {
        val list = ArrayList<BanjirResponse>()
        try {
            val responseObject = JSONObject(parsingFileToString("BanjirResponses.json").toString())
            val listArray = responseObject.getJSONArray("banjir")
            for (i in 0 until listArray.length()) {
                val banjir = listArray.getJSONObject(i)

                val idbanjir = banjir.getString("idbanjir")
                val location = banjir.getString("location")
                val city = banjir.getString("city")
                val condition = banjir.getBoolean("condition")
                val imagePath = banjir.getString("imagePath")

                val courseResponse = BanjirResponse(idbanjir, location, city, condition, imagePath)
                list.add(courseResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return list
    }

    fun loadReport(): List<ReportResponse> {
        val list = ArrayList<ReportResponse>()
        try {
            val responseObject = JSONObject(parsingFileToString("ReportResponses.json").toString())
            val listArray = responseObject.getJSONArray("report")
            for (i in 0 until listArray.length()) {
                val report = listArray.getJSONObject(i)

                val idreport = report.getString("idreport")
                val name = report.getString("name")
                val time = report.getString("time")
                val location = report.getString("location")
                val info = report.getString("info")
                val imagePath = report.getString("imagePath")

                val reportResponse = ReportResponse(idreport, name, time, location, info, imagePath)
                list.add(reportResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return list
    }


}