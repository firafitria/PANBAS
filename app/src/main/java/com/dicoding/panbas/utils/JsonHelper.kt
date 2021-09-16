package com.dicoding.panbas.utils

import android.content.Context
import android.util.Log
import com.dicoding.panbas.data.datasource.response.BanjirResponse
import com.dicoding.panbas.data.datasource.response.ReportResponse
import org.json.JSONArray
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
                val condition = banjir.getString("condition")
                val imagePath = banjir.getString("imagePath")

                val courseResponse = BanjirResponse(idbanjir, location, city, condition, imagePath)
                list.add(courseResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return list
    }

    fun loadItemBanjir(idbanjir : String): BanjirResponse{
        val fileName = String.format("BanjirResponses.json", idbanjir)
        var banjirResponse: BanjirResponse? = null
        try {
            val result = parsingFileToString(fileName)
            if (result != null) {
                Log.e("helper", "loadItemBanjir: $result")
                val responseObject = JSONObject(result)
                val tes = responseObject.getJSONArray("banjir")
                for (i in 0 until tes.length()) {
                    Log.e("HELPER", "loadItemBanjir: ${tes.getJSONObject(i)}", )
                    val id = tes.getJSONObject(i).getString("idbanjir")
                    if (idbanjir.equals(id, ignoreCase = true)) {
                        val location = tes.getJSONObject(i).getString("location")
                        val city = tes.getJSONObject(i).getString("city")
                        val condition = tes.getJSONObject(i).getString("condition")
                        val imagePath = tes.getJSONObject(i).getString("imagePath")
                        banjirResponse = BanjirResponse(idbanjir,location,city,condition,imagePath)
                    }
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
            Log.e("helper", "loadItemBanjir: error ${e.message}", e)
        }

        return banjirResponse as BanjirResponse

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

    fun loadItemReport(idreport :String): ReportResponse{
        val fileName = String.format("ReportResponses.json", idreport)
        var reportResponse: ReportResponse? = null
        try {
            val result = parsingFileToString(fileName)
            if (result != null) {
                Log.e("helper", "loadItemReport: $result")
                val responseObject = JSONObject(result)
                val report = responseObject.getJSONArray("report")
                for (i in 0 until report.length()) {
                    Log.e("HELPER", "loadItemReport: ${report.getJSONObject(i)}", )
                    val id = report.getJSONObject(i).getString("idreport")
                    if (idreport.equals(id, ignoreCase = true)) {
                        val name = report.getJSONObject(i).getString("name")
                        val time = report.getJSONObject(i).getString("time")
                        val location = report.getJSONObject(i).getString("location")
                        val info = report.getJSONObject(i).getString("info")
                        val imagePath = report.getJSONObject(i).getString("imagePath")
                        reportResponse = ReportResponse(idreport,name,time,location,info,imagePath)
                    }
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
            Log.e("helper", "loadItemReport: error ${e.message}", e)
        }

        return reportResponse as ReportResponse

    }


}