package com.dicoding.panbas.data.datasource.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dicoding.panbas.data.datasource.local.dao.ReportDao
import com.dicoding.panbas.data.datasource.local.entity.ReportEntity


@Database(
    entities = [ReportEntity::class], version = 1)
abstract class PanbasDatabase : RoomDatabase() {

    abstract fun reportDao(): ReportDao

    companion object {
        @Volatile
        private var instance: PanbasDatabase? = null

        fun getInstance(context: Context): PanbasDatabase {
            if (instance == null) {
                synchronized(this) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PanbasDatabase::class.java, "panbas")
                        .build()
                }
            }
            return instance as PanbasDatabase
        }
    }
}