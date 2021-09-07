package com.dicoding.panbas.utils

import android.content.Context
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
                val date = banjir.getString("date")
                val condition = banjir.getBoolean("condition")
                val imagePath = banjir.getString("imagePath")

                val courseResponse = BanjirResponse(idbanjir, location, city, date, condition, imagePath)
                list.add(courseResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return list
    }

}