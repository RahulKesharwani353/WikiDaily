package com.example.newsify.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newsify.Models.ArticlesItem
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized
import java.time.Instant

@Database(entities = [ArticlesItem::class], version = 1)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun newsDatabase(): NewsDao

    companion object{
        @Volatile
        private var INSTANCE: NewsDatabase?= null

        @OptIn(InternalCoroutinesApi::class)
        fun getDatabase(context: Context): NewsDatabase{
            if (INSTANCE== null){
                synchronized(this){
                    INSTANCE =  Room.databaseBuilder(context, NewsDatabase::class.java,
                    "newsDB").build()
                }
            }
            return INSTANCE!!
        }
    }
}