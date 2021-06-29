package com.example.newsapp.Data.Network

import com.example.newsapp.Utlies.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val getApi: Api by lazy {
        retrofit.create(Api::class.java)
    }
}