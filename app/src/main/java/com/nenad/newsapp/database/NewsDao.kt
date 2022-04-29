package com.nenad.newsapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nenad.newsapp.database.model.apiresponse.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Query("SELECT * FROM articles")
    fun readResults() : Flow<List<Article>> //LiveData<List<Article>>

    @Insert(onConflict = OnConflictStrategy.REPLACE) //onConflict determines if the article already exists
    suspend fun insertUpdateArticle(article: Article) : Long

    @Delete
    suspend fun deleteArticle(article: Article)

    @Query("DELETE FROM articles")
    suspend fun deleteAllSavedArticles()




}