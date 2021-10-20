package com.example.newsapp

import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("v2/everything")
    fun getNews(
        @Query("q") query: String,
        @Query("apiKey") apiKey: String
    ): Call<NewsModel>
}