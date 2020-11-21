package com.example.nytimesreader.ui.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.nytimesreader.R
import com.example.nytimesreader.data.model.Story
import com.example.nytimesreader.ui.viewmodel.BookmarkViewModel
import com.example.nytimesreader.ui.viewmodel.ViewModelFactory
import com.example.nytimesreader.util.BUNDLE_KEY
import com.example.nytimesreader.util.HAS_BOOKMARKED
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity :AppCompatActivity(), View.OnClickListener{
    private lateinit var story: Story
    private lateinit var bookMarkModel: BookmarkViewModel
    val gson = Gson()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        try {
            intent.extras?.let {
                story = gson.fromJson(it.getString(BUNDLE_KEY), Story::class.java)
                println(story)
            }
        }catch (e:Exception){
            print(e.message)
        }

        Glide.with(this)
            .load(story.image)
            .centerCrop()
            .into(detailImage)


        detailTitle.text = story.title
        detailPublished.text = story.published
        detailArticle.text = story.article


        intent.extras?.let {
            if(it.getBoolean(HAS_BOOKMARKED)){
                detailButton.isEnabled = false
                detailButton.isClickable = false
            }else{
                detailButton.setOnClickListener(this)
            }
        }

        detailLink.setOnClickListener(View.OnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(story.url)
            startActivity(intent)
        })


    }

    override fun onClick(p0: View?) {
        val viewModelFactory = ViewModelFactory(this)
        bookMarkModel = ViewModelProvider(this, viewModelFactory).get(BookmarkViewModel::class.java)
        bookMarkModel.insertBookmark(story).observe(this, Observer {
            println(it)
            Toast.makeText(this@DetailActivity, "bookmark added", Toast.LENGTH_LONG).show()
            Thread.sleep(1000)

            Intent(this@DetailActivity, MainActivity::class.java).apply {
                startActivity(this)
            }
        })
    }
}