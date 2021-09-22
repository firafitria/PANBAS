package com.dicoding.panbas.data.datasource.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dicoding.panbas.data.datasource.local.entity.ReportEntity

@Dao
interface ReportDao {
    @Query("SELECT * FROM reportEntity")
    fun getAllReport(): LiveData<List<ReportEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertReport(list: List<ReportEntity>)
}