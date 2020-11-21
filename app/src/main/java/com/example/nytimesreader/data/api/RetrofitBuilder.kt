package com.example.nytimesreader.data.api

import com.example.nytimesreader.util.BASE_URL

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//initialize retrofit. Use the singleton pattern
object RetrofitBuilder {
    val retrofitBuilder:Retrofit.Builder by lazy {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())

    }

    val nytimeService:NytimesApiService by lazy {
        retrofitBuilder
            .build()
            .create(NytimesApiService::class.java)
    }

}