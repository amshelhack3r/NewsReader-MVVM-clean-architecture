package com.example.nytimesreader.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.nytimesreader.data.model.Story


//initialize a room database
@Database(entities = arrayOf(Story::class), version = 1, exportSchema = false)
abstract class StoryDatabase:RoomDatabase() {
     abstract  fun storyDao():StoryDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: StoryDatabase? = null

        fun getDatabase(context: Context): StoryDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    StoryDatabase::class.java,
                    "stories"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}