package com.example.nytimesreader.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.nytimesreader.R
import com.example.nytimesreader.data.model.Story
import com.example.nytimesreader.ui.adapter.MyBookmarkRecyclerViewAdapter
import com.example.nytimesreader.ui.adapter.TopStoriesRecyclerAdapter
import com.example.nytimesreader.ui.viewmodel.BookmarkViewModel
import com.example.nytimesreader.ui.viewmodel.ViewModelFactory
import com.example.nytimesreader.util.BUNDLE_KEY
import com.example.nytimesreader.util.HAS_BOOKMARKED
import com.google.gson.Gson

/**
 * A fragment representing a list of Items.
 */
class BookmarkFragment : Fragment(), MyBookmarkRecyclerViewAdapter.OnGridListener{

    private var columnCount = 2
    private lateinit var viewModel: BookmarkViewModel
    private lateinit var stories: List<Story>
    val listener: MyBookmarkRecyclerViewAdapter.OnGridListener = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }

        val viewModelFactory = ViewModelFactory(this.requireContext())
        viewModel = ViewModelProvider(this, viewModelFactory).get(BookmarkViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bookmark_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                viewModel.bookmarks.observe(viewLifecycleOwner, Observer {
                    try {
                        stories = it
                        adapter = MyBookmarkRecyclerViewAdapter(it, listener)
                    }catch (e:Exception){
                        println("Exception: "+e.message)
                    }
                })

            }
        }
        return view
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            BookmarkFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }

    override fun onClick(position: Int) {
        val story = stories[position]
        val gson = Gson()
        //convert story model to a string and send it through a bundle object
        Intent(activity, DetailActivity::class.java).apply {
            putExtras(Bundle().apply {
                putString(BUNDLE_KEY, gson.toJson(story))
                putBoolean(HAS_BOOKMARKED, true)
            })
            startActivity(this)
        }
    }
}

