package com.dicoding.panbas.utils

import android.content.Context
import android.util.Log
import com.dicoding.panbas.data.datasource.response.BanjirResponse
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



}