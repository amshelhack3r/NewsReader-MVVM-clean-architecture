package com.example.nytimesreader.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(val context: Context):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookmarkViewModel::class.java)) {
            return BookmarkViewModel(context) as T
        }else if (modelClass.isAssignableFrom(TopStoriesViewModel::class.java)){
            return TopStoriesViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}