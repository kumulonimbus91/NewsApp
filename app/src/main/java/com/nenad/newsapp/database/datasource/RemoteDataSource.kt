package com.nenad.newsapp.database.datasource

import com.nenad.newsapp.database.model.apiresponse.Result
import com.nenad.newsapp.database.network.RetrofitInstance
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor() {
    suspend fun getHeadlines(): Response<Result> {
        return RetrofitInstance.api.getHeadLines()
    }
    suspend fun getNewsByCategory(): Response<Result> {
        return RetrofitInstance.api.getNewsByCategory("general")
    }

    suspend fun getSportNews(): Response<Result> {
        return RetrofitInstance.api.getSportsNews("sports")
    }

    suspend fun getBusinessNews(): Response<Result> {
        return RetrofitInstance.api.getBusinessNews("business")
    }

    suspend fun getEntertainmentNews(): Response<Result> {
        return RetrofitInstance.api.getEntertainmentNews("entertainment")
    }
    suspend fun getHealthNews(): Response<Result> {
        return RetrofitInstance.api.getHealthNews("health")
    }
    suspend fun getScienceNews(): Response<Result> {
        return RetrofitInstance.api.getScienceNews("science")
    }
    suspend fun getTechNews(): Response<Result> {
        return RetrofitInstance.api.getTechNews("technology")
    }

    suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        RetrofitInstance.api.searchNews(searchQuery, pageNumber)

}

//private val apiService: ApiServiceInterface
//apiService.getHeadLines(Constants.country, Constants.API_KEY)