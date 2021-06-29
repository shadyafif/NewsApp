package com.example.newsapp.Data.Network

import com.example.newsapp.Data.Model.Main
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("top-headlines")
    suspend fun getHeadlines(
        @Query("apiKey") key: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Main


    @GET("top-headlines")
    suspend fun getBusiness(
        @Query("apiKey") key: String,
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("page") page: Int
    ): Main

}