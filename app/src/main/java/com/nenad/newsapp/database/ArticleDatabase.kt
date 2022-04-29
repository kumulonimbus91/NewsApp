package com.nenad.newsapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nenad.newsapp.database.model.apiresponse.Article

@Database(entities = [Article::class], version = 1)
@TypeConverters(com.nenad.newsapp.database.TypeConverters::class)
abstract class ArticleDatabase: RoomDatabase() { //recreate database as singleton

    abstract fun getDao(): NewsDao

//    companion object {
//        @Volatile
//        private var instance: ArticleDatabase? = null
//        private val LOCK = Any() //only a single instance of database at once
//
//        operator fun invoke(context:Context) = instance ?: synchronized(LOCK) { // make sure there is no another thread that sets this instance
//          instance ?: createDatabase(context).also{ instance = it }
//        }
//
//        private fun createDatabase(context: Context) =
//            Room.databaseBuilder(context.applicationContext, ArticleDatabase::class.java, "article_db.db").build()
//
//
//
//
//    }




}