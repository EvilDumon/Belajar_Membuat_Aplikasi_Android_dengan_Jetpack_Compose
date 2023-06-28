package com.example.weatherapp.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.weatherapp.data.local.entity.BookmarkEntity

@Dao
interface BookmarkDao {
    @Query("SELECT * FROM bookmarkCity ORDER BY cityName ASC")
    fun getBookmarkCity(): LiveData<List<BookmarkEntity>>

    @Query("SELECT * FROM bookmarkCity WHERE cityName = :cityName")
    fun getBookmarkCity(cityName: String): LiveData<BookmarkEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCity(user: BookmarkEntity)

    @Query("DELETE FROM bookmarkCity WHERE cityName = :cityName")
    fun deleteCity(cityName: String)
}