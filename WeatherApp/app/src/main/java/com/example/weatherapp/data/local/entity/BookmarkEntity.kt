package com.example.weatherapp.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "bookmarkCity")
@Parcelize
class BookmarkEntity(
    @ColumnInfo(name = "cityName")
    @PrimaryKey(autoGenerate = false)
    val cityName: String

) : Parcelable