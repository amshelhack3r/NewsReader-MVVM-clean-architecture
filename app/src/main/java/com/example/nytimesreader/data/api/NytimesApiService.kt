package com.example.nytimesreader.data.api

import com.example.nytimesreader.util.API_KEY
import com.google.gson.JsonObject
import retrofit2.http.GET

//build the retrofit endpoints
public interface NytimesApiService {
    @GET("arts.json?api-key="+ API_KEY)
    suspend fun getStories():JsonObject
}