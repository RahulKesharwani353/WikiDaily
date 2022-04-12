package com.example.newsify.db
//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import com.example.wikidaily.FeaturedImages.Models.ImageinfoItem
//import com.example.wikidaily.FeaturedImages.Models.Page
//import kotlinx.coroutines.InternalCoroutinesApi
//import kotlinx.coroutines.internal.synchronized
//
//@Database(entities = [Page::class], version = 1.1.toInt())
//abstract class ImagesDatabase : RoomDatabase() {
//
//    abstract fun newsDatabase(): ImagesDao
//
//    companion object{
//        @Volatile
//        private var INSTANCE: ImagesDatabase?= null
//
//        @OptIn(InternalCoroutinesApi::class)
//        fun getDatabase(context: Context): ImagesDatabase{
//            if (INSTANCE== null){
//                synchronized(this){
//                    INSTANCE =  Room.databaseBuilder(context, ImagesDatabase::class.java,
//                    "imagesDB").build()
//                }
//            }
//            return INSTANCE!!
//        }
//    }
//}