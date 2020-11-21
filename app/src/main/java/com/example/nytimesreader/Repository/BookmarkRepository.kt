package com.example.nytimesreader.Repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.nytimesreader.data.api.RetrofitBuilder
import com.example.nytimesreader.data.db.StoryDatabase
import com.example.nytimesreader.data.model.Story
import com.google.gson.Gson
import com.google.gson.JsonArray
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray

object BookmarkRepository {
    fun getBookmarks(storyDatabase: StoryDatabase):LiveData<List<Story>>{
        return object:LiveData<List<Story>>(){
            override fun onActive() {
                super.onActive()
                CoroutineScope(Dispatchers.IO).launch {
                         val stories = storyDatabase.storyDao().getBookmarks()
                        withContext(Dispatchers.Main){
                        value = stories
                    }
                }
            }
        }


    }
    fun insertBookmark(story:Story, storyDatabase: StoryDatabase):LiveData<Boolean>{
        return object :LiveData<Boolean>(){
            override fun onActive() {
                super.onActive()
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        storyDatabase.storyDao().insertBookmark(story)
                        withContext(Main) {
                            value = true
                        }
                    }catch (e:Exception){
                        println(e.message)
                    }
                }
            }
        }
    }
}