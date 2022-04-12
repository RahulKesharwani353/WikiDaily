package com.example.newsify.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.wikidaily.FeaturedImages.Models.ImageinfoItem


@Dao
interface NewsDao {

    @Insert
    suspend fun addNes(images: ArrayList<ImageinfoItem>)

    @Query("SELECT * FROM images")
    suspend fun getNews(): List<ImageinfoItem>
}