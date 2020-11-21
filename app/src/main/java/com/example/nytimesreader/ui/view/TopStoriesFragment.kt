package com.example.nytimesreader.ui.view

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nytimesreader.R
import com.example.nytimesreader.data.model.Story
import com.example.nytimesreader.ui.adapter.TopStoriesRecyclerAdapter
import com.example.nytimesreader.ui.viewmodel.BookmarkViewModel
import com.example.nytimesreader.ui.viewmodel.TopStoriesViewModel
import com.example.nytimesreader.ui.viewmodel.ViewModelFactory
import com.example.nytimesreader.util.BUNDLE_KEY
import com.google.gson.Gson
import kotlinx.android.synthetic.main.recycler.*

/**
 * A placeholder fragment containing a simple view.
 */
class TopStoriesFragment : Fragment(), TopStoriesRecyclerAdapter.onRowListener {

    private lateinit var viewModel: TopStoriesViewModel

    private lateinit var stories: List<Story>
    val listener:TopStoriesRecyclerAdapter.onRowListener = this


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModelFactory = ViewModelFactory(this.requireContext())
        viewModel = ViewModelProvider(this, viewModelFactory).get(TopStoriesViewModel::class.java)


    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.recycler, container, false)
        val recycler:RecyclerView = root.findViewById(R.id.recyclerView)
        val layoutManager = LinearLayoutManager(context)
        recycler.layoutManager = layoutManager
        recycler.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        recycler.isNestedScrollingEnabled = false

         try {
            viewModel.getStories.observe(viewLifecycleOwner, Observer {
                progress.visibility = View.INVISIBLE
                stories = it
                try {
                    recycler.adapter = TopStoriesRecyclerAdapter(it, listener)
                }catch (e:Exception){
                    println("Exception: "+e.message)
                }
            })
        }catch (e:Exception){
            println(e.message)
        }



        return root
    }
    override fun onRowClick(position: Int) {
        val story = stories[position]
        val gson = Gson()
        //convert story model to a string and send it through a bundle object
        Intent(activity, DetailActivity::class.java).apply {
            putExtras(Bundle().apply {
                putString(BUNDLE_KEY, gson.toJson(story))
            })
            startActivity(this)
        }


    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): TopStoriesFragment {
            return TopStoriesFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}