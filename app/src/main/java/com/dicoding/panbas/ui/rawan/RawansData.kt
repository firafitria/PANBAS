package com.dicoding.panbas.ui.rawan

import com.dicoding.panbas.data.datasource.local.entity.ListEntity

object RawansData {

    private val locationNames = arrayOf("jalan Slamet Riyadi",
        "jalan Slamet Riyadi",
        "jalan Pramuka",
        "jalan Abdul Wahab Syahranie",
        "jalan Diponegoro",
        "jalan Dr. Sutomo",
        "jalan Wahid Hasyim II",
        "jalan Juanda",
        "jalan Kadrie Oening",
        "jalan Antasari",
        "jalan Suryanata",
        "jalan MT Haryono",
        "jalan Siradj Salman",
        "jalan Bhayangkara",
        "jalan Lempake",
        "jalan Hasan Basri",
        "jalan Gatot Subroto",
        "jalan Cendrawasih",
        "jalan M. Yamin",
        "jalan Lambung Mangkurat",
        "jalan Gerilya")

    val listData: ArrayList<ListEntity>
        get() {
            val list = arrayListOf<ListEntity>()
            for (position in locationNames.indices) {
                val rawan = ListEntity()
                rawan.location = locationNames[position]
                list.add(rawan)
            }
            return list
        }
}