package com.nenad.newsapp.di

import android.content.Context
import androidx.room.Room
import com.nenad.newsapp.database.ArticleDatabase
import com.nenad.newsapp.utils.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, ArticleDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideDatabaseDao(articlesDatabase: ArticleDatabase) = articlesDatabase.getDao()
}