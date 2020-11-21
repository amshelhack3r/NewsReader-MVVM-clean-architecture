package com.example.nytimesreader.data.db


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.nytimesreader.data.model.Story

//creat dao for inserting nad seleting users
@Dao
interface StoryDao{
    @Insert
    suspend fun insertBookmark(story:Story)

    @Query("SELECT * FROM story")
    fun getBookmarks(): List<Story>
}