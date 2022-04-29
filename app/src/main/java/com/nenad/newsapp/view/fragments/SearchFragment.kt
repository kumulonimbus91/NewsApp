package com.nenad.newsapp.view.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nenad.newsapp.R
import com.nenad.newsapp.adapters.CategoryAdapter
import com.nenad.newsapp.adapters.TopHeadlinesAdapter
import com.nenad.newsapp.database.network.Response
import com.nenad.newsapp.databinding.FragmentSearchBinding
import com.nenad.newsapp.utils.Constants.SEARCH_NEWS_TIME_DELAY
import com.nenad.newsapp.viewmodels.MainViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SearchFragment : Fragment() {

    private lateinit var mBinding: FragmentSearchBinding

    val viewModel: MainViewModel by activityViewModels<MainViewModel>()

    lateinit var topHeadlinesAdapter: TopHeadlinesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)

        setUpRecyclerView()
        setOnClickListeners()


        var job: Job? = null
        mBinding.etSearch.addTextChangedListener { editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(SEARCH_NEWS_TIME_DELAY)
                editable?.let {
                    if (editable.toString().isNotEmpty()) {
                        viewModel.searchNews(editable.toString())
                    }
                }
            }
        }

        mBinding.btnSearch.setOnClickListener {
            viewModel.searchNews.observe(viewLifecycleOwner, Observer { response ->
                when (response) {
                    is Response.Success -> {
                        hideProgressBar()
                        response.data?.let { newsResponse ->
                            topHeadlinesAdapter.differ.submitList(newsResponse.articles)
                        }
                    }
                    is Response.Error -> {
                        hideProgressBar()
                        response.message?.let { message ->
                            Log.e(TAG, "An error occured: $message")
                        }
                    }
                    is Response.Loading -> {
                        showProgressBar()
                    }
                }
            })
        }














        return mBinding.root
    }

    private fun hideProgressBar() {

    }

    private fun showProgressBar() {

    }

    private fun setUpRecyclerView() {
        topHeadlinesAdapter = TopHeadlinesAdapter(this)
        mBinding.recyclerView.apply {
            adapter = topHeadlinesAdapter
            layoutManager = LinearLayoutManager(activity)
        }


    }

    private fun setOnClickListeners() {
        topHeadlinesAdapter.setOnClickListener {
            val action = SearchFragmentDirections.actionSearchFragmentToOverviewFragment(it)
            findNavController().navigate(action)
        }

    }
}