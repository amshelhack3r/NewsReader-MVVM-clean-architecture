package com.example.nytimesreader.data.model

import com.google.gson.annotations.SerializedName

//create a data class to hold our article response
//use serialized annotations to easily convert Gson to Model
class Story(
    @SerializedName("title")
    val title:String,

    @SerializedName("abstract")
    val abstract:String,

    @SerializedName("url")
    val url:String,

    @SerializedName("published_date")
    val published:String,

    @SerializedName("multimedia")
    val multimedia:Array<HashMap<String, Any>>
){
//    retrieve first instance of the image from the array
    val image:String
        get()= (this.multimedia[0].get("url")?:"") as String

    override fun toString(): String {
        return "$title $url"
    }
}