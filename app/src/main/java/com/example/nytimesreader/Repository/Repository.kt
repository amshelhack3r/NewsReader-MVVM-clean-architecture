package com.example.nytimesreader.Repository
import androidx.lifecycle.LiveData
import com.example.nytimesreader.data.api.RetrofitBuilder
import com.example.nytimesreader.data.model.Story
import com.google.gson.Gson
import com.google.gson.JsonArray
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object Repository {

    fun getStories():LiveData<List<Story>>{
        return object:LiveData<List<Story>>(){
            override fun onActive() {
                super.onActive()
                CoroutineScope(Dispatchers.IO).launch {
                    val response = RetrofitBuilder.nytimeService.getStories()
                    val stories = response.get("results") as JsonArray
                    stories.map {
                        var x = it.asJsonObject.get("multimedia") as JsonArray
                        it.asJsonObject.add("image", x[0].asJsonObject.get("url"))
                    }

                    withContext(Main){
                        try {
                            value = stories.map { story ->

                                val gson = Gson()
                                gson.fromJson(story, Story::class.java)
                            }
                        }catch (e:Exception){
                            println("Converting"+e.message)
                        }
                    }
                }
            }
        }
    }
}