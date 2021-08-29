package com.example.tripassistant.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    fun get(): Retrofit {

        return Retrofit.Builder()
            .baseUrl(LocationApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}