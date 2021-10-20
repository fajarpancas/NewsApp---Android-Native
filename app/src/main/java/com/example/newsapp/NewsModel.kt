package com.example.newsapp

import java.util.*

data class NewsModel (
    val status: String,
    val totalResults: Int,
    val articles: ArrayList<Result>
){
    data class Result (
        val title: String,
        val description: String,
        val urlToImage: String,
        val publishedAt: String,
        val content: String
    )
}
