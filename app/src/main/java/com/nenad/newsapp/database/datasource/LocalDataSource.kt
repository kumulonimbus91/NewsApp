package com.nenad.newsapp.database.datasource

import com.nenad.newsapp.database.ArticleDatabase
import com.nenad.newsapp.database.NewsDao
import com.nenad.newsapp.database.model.apiresponse.Article
import com.nenad.newsapp.database.network.ApiServiceInterface
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val dao: NewsDao) {



    suspend fun insertArticle(article: Article) {


        dao.insertUpdateArticle(article)
    }

    fun readArticles(): Flow<List<Article>> {
        return dao.readResults()
    }

    suspend fun deleteAllArticles() {
        dao.deleteAllSavedArticles()
    }

    suspend fun deleteArticle(article: Article) {
        dao.deleteArticle(article)
    }


}