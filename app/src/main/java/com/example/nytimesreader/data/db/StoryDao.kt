package com.example.nytimesreader.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.nytimesreader.data.model.Story

@Dao
interface StoryDao{
    @Insert
    suspend fun insertBookmark(story:Story)

    @Query("SELECT * FROM story")
    fun getBookmarks(): List<Story>
}