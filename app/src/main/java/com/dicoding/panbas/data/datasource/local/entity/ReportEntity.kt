package com.dicoding.panbas.data.datasource.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "reportEntity")
data class ReportEntity (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "lokasi")
    var lokasi: String?,

    @ColumnInfo(name = "keterangan")
    var keterangan: String?,

    @ColumnInfo(name = "linkFoto")
    var linkFoto: String?,
    ) : Parcelable