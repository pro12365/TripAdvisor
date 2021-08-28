package com.example.tripassistant.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    fun get(): Retrofit {

        return Retrofit.Builder()
            .baseUrl("https://www.triposo.com/api/20210615/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}