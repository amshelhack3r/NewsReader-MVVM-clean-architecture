package com.example.nytimesreader.ui.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.nytimesreader.Repository.Repository
import com.example.nytimesreader.data.model.Story

class TopStoriesViewModel(context: Context) : ViewModel() {
    val getStories:LiveData<List<Story>> =  Repository.getStories()
}