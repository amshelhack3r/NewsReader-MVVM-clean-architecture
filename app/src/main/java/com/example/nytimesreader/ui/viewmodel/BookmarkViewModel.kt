package com.example.nytimesreader.ui.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.nytimesreader.Repository.BookmarkRepository
import com.example.nytimesreader.Repository.Repository
import com.example.nytimesreader.data.db.StoryDatabase
import com.example.nytimesreader.data.model.Story
import com.example.nytimesreader.util.EventManager
import kotlinx.coroutines.Dispatchers

class BookmarkViewModel(context: Context): ViewModel() {

    private val storyDatabase = StoryDatabase.getDatabase(context)
    val bookmarks = BookmarkRepository.getBookmarks(storyDatabase)
    fun insertBookmark(story: Story) = BookmarkRepository.insertBookmark(story, storyDatabase)
}