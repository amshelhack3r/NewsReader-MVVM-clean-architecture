package com.example.nytimesreader.data.api

import androidx.lifecycle.LiveData
import com.example.nytimesreader.data.model.Story
import com.example.nytimesreader.util.API_KEY
import com.google.gson.JsonObject
import retrofit2.http.GET

public interface NytimesApiService {
    @GET("arts.json?api-key="+ API_KEY)
    suspend fun getStories():JsonObject
}