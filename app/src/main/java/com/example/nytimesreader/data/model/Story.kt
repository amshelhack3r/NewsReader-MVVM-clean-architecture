package com.example.nytimesreader.data.model

import android.os.Build
import androidx.annotation.Nullable
import androidx.annotation.RequiresApi
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.time.LocalDate
import java.time.format.DateTimeFormatter

//create a data class to hold our article response
//use serialized annotations to easily convert Gson to Model
@Entity(tableName = "story")
class Story(
    @PrimaryKey(autoGenerate = true)
    val id:Int,

    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title:String,

    @ColumnInfo(name = "article")
    @SerializedName("abstract")
    val article:String,

    @ColumnInfo(name = "url")
    @SerializedName("url")
    val url:String,

    @ColumnInfo(name = "date")
    @SerializedName("published_date")
    val published:String,

    @Nullable
    @ColumnInfo(name = "image")
    val image:String?,

){

    val formattedDate:String
        @RequiresApi(Build.VERSION_CODES.O)
        get() = LocalDate.parse(published, DateTimeFormatter.ofPattern("dd-MM-yyyy")).toString()

    override fun toString(): String {
        return "$title $url"
    }
}