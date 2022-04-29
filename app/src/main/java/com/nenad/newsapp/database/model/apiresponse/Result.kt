package com.nenad.newsapp.database.model.apiresponse


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("articles")
    val articles: List<Article>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)