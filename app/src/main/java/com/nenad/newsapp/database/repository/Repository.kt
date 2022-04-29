package com.nenad.newsapp.database.repository

import com.nenad.newsapp.database.datasource.LocalDataSource
import com.nenad.newsapp.database.datasource.RemoteDataSource
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject
@ViewModelScoped
class Repository @Inject constructor(val remote: RemoteDataSource, val local: LocalDataSource) {


    suspend fun getHeadlinesRepo() = remote.getHeadlines()


    suspend fun getSportsNews() = remote.getSportNews()

    suspend fun getBusinessNews() = remote.getBusinessNews()

    suspend fun getEntertainmentNews() = remote.getEntertainmentNews()

    suspend fun getHealthNews() = remote.getHealthNews()

    suspend fun getScienceNews() = remote.getScienceNews()

    suspend fun getTechNews() = remote.getTechNews()








}