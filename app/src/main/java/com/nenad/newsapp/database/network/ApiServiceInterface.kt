package com.nenad.newsapp.database.network

import androidx.databinding.library.baseAdapters.BuildConfig
import com.nenad.newsapp.database.model.apiresponse.Result
import com.nenad.newsapp.utils.Constants
import com.nenad.newsapp.utils.Constants.API_KEY_N
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

//private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()


//private val retrofit = Retrofit.Builder()
//    .baseUrl(Constants.BASE_URL).addConverterFactory(MoshiConverterFactory.create(moshi))
//    .addCallAdapterFactory(CoroutineCallAdapterFactory()).baseUrl(Constants.BASE_URL)
//    .build()


interface ApiServiceInterface {


    @GET(Constants.END_POINT)
    suspend fun getHeadLines(
        @Query("country") country: String = Constants.country,
        @Query("page") pageNumber: Int = 1,
        @Query("apiKey") apiKey: String = API_KEY_N
    ): Response<Result>

    @GET(Constants.END_POINT_SEARCH)
    suspend fun searchNews(
        @Query("q") searchQuery: String,
        @Query("page") pageNumber: Int = 1,
        @Query("apiKey") apiKey: String = API_KEY_N
    ): Response<Result>

    @GET(Constants.END_POINT)
    suspend fun getSportsNews(
        @Query("category") category: String,
        @Query("country") country: String = Constants.country,
        @Query("page") pageNumber: Int = 1,
        @Query("apiKey") apiKey: String = API_KEY_N
    ): Response<Result>

    @GET(Constants.END_POINT)
    suspend fun getBusinessNews(
        @Query("category") category: String,
        @Query("country") country: String = Constants.country,
        @Query("page") pageNumber: Int = 1,
        @Query("apiKey") apiKey: String = API_KEY_N
    ): Response<Result>

    @GET(Constants.END_POINT)
    suspend fun getEntertainmentNews(
        @Query("category") category: String,
        @Query("country") country: String = Constants.country,
        @Query("page") pageNumber: Int = 1,
        @Query("apiKey") apiKey: String = API_KEY_N
    ): Response<Result>

    @GET(Constants.END_POINT)
    suspend fun getHealthNews(
        @Query("category") category: String,
        @Query("country") country: String = Constants.country,
        @Query("page") pageNumber: Int = 1,
        @Query("apiKey") apiKey: String = API_KEY_N
    ): Response<Result>

    @GET(Constants.END_POINT)
    suspend fun getScienceNews(
        @Query("category") category: String,
        @Query("country") country: String = Constants.country,
        @Query("page") pageNumber: Int = 1,
        @Query("apiKey") apiKey: String = API_KEY_N
    ): Response<Result>

    @GET(Constants.END_POINT)
    suspend fun getTechNews(
        @Query("category") category: String,
        @Query("country") country: String = Constants.country,
        @Query("page") pageNumber: Int = 1,
        @Query("apiKey") apiKey: String = API_KEY_N
    ): Response<Result>


}

object ApiService {
    val apiservice: ApiServiceInterface by lazy { RetrofitInstance.api }

    //retrofit.create(ApiServiceInterface::class.java)
}