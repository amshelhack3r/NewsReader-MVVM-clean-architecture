package com.example.nytimesreader.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nytimesreader.util.EventManager

class ViewModelFactory(val eventManager: EventManager):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(
                eventManager
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}