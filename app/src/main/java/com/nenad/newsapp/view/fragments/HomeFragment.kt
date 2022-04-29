package com.nenad.newsapp.view.fragments

import android.content.SharedPreferences
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.button.MaterialButton
import com.nenad.newsapp.R
import com.nenad.newsapp.adapters.CategoryAdapter
import com.nenad.newsapp.adapters.TopHeadlinesAdapter
import com.nenad.newsapp.databinding.FragmentHomeBinding
import com.nenad.newsapp.view.activities.MainActivity
import com.nenad.newsapp.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : Fragment()  {

    private lateinit var mBinding: FragmentHomeBinding

    val viewModel: MainViewModel by activityViewModels<MainViewModel>() //MainViewModel by viewModels()

    lateinit var topHeadlinesAdapter: TopHeadlinesAdapter

    lateinit var categoryAdapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        mBinding.lifecycleOwner = this






        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)






        setUpRecyclerView()
        setOnClickListeners()

        mBinding.sportsBtn.background = ContextCompat.getDrawable(requireActivity(), R.drawable.button_selected)


        lifecycleScope.launch {

            viewModel.result.observe(viewLifecycleOwner, Observer { response ->
                when (response) {
                    is com.nenad.newsapp.database.network.Response.Success -> {
                        hideProgressBar()
                        response.data.let {
                            topHeadlinesAdapter.differ.submitList(it?.articles)
                        }
                    }
                    is com.nenad.newsapp.database.network.Response.Error -> {
                        hideProgressBar()
                        response.message.let {
                            Log.e("TAG", "ERROR")

                        }
                    }
                    is com.nenad.newsapp.database.network.Response.Loading -> {
                        showProgressBar()
                    }
                }
            })

            viewModel.resultCategory.observe(viewLifecycleOwner, Observer { response ->

                when (response) {
                    is com.nenad.newsapp.database.network.Response.Success -> {
                        hideProgressBar()
                        response.data.let {
                            categoryAdapter.differ.submitList(it?.articles)
                        }
                    }
                    is com.nenad.newsapp.database.network.Response.Error -> {
                        hideProgressBar()
                        response.message.let {
                            Log.e("TAG", "ERROR")

                        }

                    }
                }
            })
        }


    }

    fun showProgressBar() {
        mBinding.progressbar.visibility = View.VISIBLE

    }

    fun hideProgressBar() {
        mBinding.progressbar.visibility = View.INVISIBLE

    }

    private fun setOnClickListeners() {
        topHeadlinesAdapter.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToOverviewFragment(it)
            findNavController().navigate(action)
        }

        categoryAdapter.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToOverviewFragment(it)
            findNavController().navigate(action)
        }
        mBinding.sportsBtn.setOnClickListener {
            viewModel.getSportNews()
            mBinding.sportsBtn.background = ContextCompat.getDrawable(requireActivity(), R.drawable.button_selected)
            mBinding.entertainmentBtn.background = ContextCompat.getDrawable(requireActivity(), R.drawable.button_not_selected)
            mBinding.scienceBtn.background = ContextCompat.getDrawable(requireActivity(), R.drawable.button_not_selected)
            mBinding.techBtn.background = ContextCompat.getDrawable(requireActivity(), R.drawable.button_not_selected)
            mBinding.healthBtn.background = ContextCompat.getDrawable(requireActivity(), R.drawable.button_not_selected)
            mBinding.businessButton.background = ContextCompat.getDrawable(requireActivity(), R.drawable.button_not_selected)
            viewModel.resultCategory.observe(viewLifecycleOwner, Observer { response ->
                when (response) {
                    is com.nenad.newsapp.database.network.Response.Success -> {
                        hideProgressBar()
                        response.data.let {
                            categoryAdapter.differ.submitList(it?.articles)
                        }
                    }
                    is com.nenad.newsapp.database.network.Response.Error -> {
                        hideProgressBar()
                        response.message.let {
                            Log.e("TAG", "ERROR")

                        }

                    }
                }

            })

        }
        mBinding.businessButton.setOnClickListener {
            viewModel.getBusinessNews()
            mBinding.sportsBtn.background = ContextCompat.getDrawable(requireActivity(), R.drawable.button_not_selected)
            mBinding.entertainmentBtn.background = ContextCompat.getDrawable(requireActivity(), R.drawable.button_not_selected)
            mBinding.scienceBtn.background = ContextCompat.getDrawable(requireActivity(), R.drawable.button_not_selected)
            mBinding.techBtn.background = ContextCompat.getDrawable(requireActivity(), R.drawable.button_not_selected)
            mBinding.healthBtn.background = ContextCompat.getDrawable(requireActivity(), R.drawable.button_not_selected)
            mBinding.businessButton.background = ContextCompat.getDrawable(requireActivity(), R.drawable.button_selected)

            viewModel.resultCategory.observe(viewLifecycleOwner, Observer { response ->
                when (response) {
                    is com.nenad.newsapp.database.network.Response.Success -> {
                        hideProgressBar()
                        response.data.let {
                            categoryAdapter.differ.submitList(it?.articles)
                        }
                    }
                    is com.nenad.newsapp.database.network.Response.Error -> {
                        hideProgressBar()
                        response.message.let {
                            Log.e("TAG", "ERROR")

                        }

                    }
                }

            })
        }
        mBinding.entertainmentBtn.setOnClickListener {
            viewModel.getEntertainmentNews()
            mBinding.sportsBtn.background = ContextCompat.getDrawable(requireActivity(), R.drawable.button_not_selected)
            mBinding.entertainmentBtn.background = ContextCompat.getDrawable(requireActivity(), R.drawable.button_selected)
            mBinding.scienceBtn.background = ContextCompat.getDrawable(requireActivity(), R.drawable.button_not_selected)
            mBinding.techBtn.background = ContextCompat.getDrawable(requireActivity(), R.drawable.button_not_selected)
            mBinding.healthBtn.background = ContextCompat.getDrawable(requireActivity(), R.drawable.button_not_selected)
            mBinding.businessButton.background = ContextCompat.getDrawable(requireActivity(), R.drawable.button_not_selected)

            viewModel.resultCategory.observe(viewLifecycleOwner, Observer { response ->
                when (response) {
                    is com.nenad.newsapp.database.network.Response.Success -> {
                        hideProgressBar()
                        response.data.let {
                            categoryAdapter.differ.submitList(it?.articles)
                        }
                    }
                    is com.nenad.newsapp.database.network.Response.Error -> {
                        hideProgressBar()
                        response.message.let {
                            Log.e("TAG", "ERROR")

                        }

                    }
                }

            })
        }
        mBinding.healthBtn.setOnClickListener {
            viewModel.getHealthNews()
            mBinding.sportsBtn.background = ContextCompat.getDrawable(requireActivity(), R.drawable.button_not_selected)
            mBinding.entertainmentBtn.background = ContextCompat.getDrawable(requireActivity(), R.drawable.button_not_selected)
            mBinding.scienceBtn.background = ContextCompat.getDrawable(requireActivity(), R.drawable.button_not_selected)
            mBinding.techBtn.background = ContextCompat.getDrawable(requireActivity(), R.drawable.button_not_selected)
            mBinding.healthBtn.background = ContextCompat.getDrawable(requireActivity(), R.drawable.button_selected)
            mBinding.businessButton.background = ContextCompat.getDrawable(requireActivity(), R.drawable.button_not_selected)

            viewModel.resultCategory.observe(viewLifecycleOwner, Observer { response ->
                when (response) {
                    is com.nenad.newsapp.database.network.Response.Success -> {
                        hideProgressBar()
                        response.data.let {
                            categoryAdapter.differ.submitList(it?.articles)
                        }
                    }
                    is com.nenad.newsapp.database.network.Response.Error -> {
                        hideProgressBar()
                        response.message.let {
                            Log.e("TAG", "ERROR")

                        }

                    }
                }

            })
        }
        mBinding.scienceBtn.setOnClickListener {
            viewModel.getScienceNews()
            mBinding.sportsBtn.background = ContextCompat.getDrawable(requireActivity(), R.drawable.button_not_selected)
            mBinding.entertainmentBtn.background = ContextCompat.getDrawable(requireActivity(), R.drawable.button_not_selected)
            mBinding.scienceBtn.background = ContextCompat.getDrawable(requireActivity(), R.drawable.button_selected)
            mBinding.techBtn.background = ContextCompat.getDrawable(requireActivity(), R.drawable.button_not_selected)
            mBinding.healthBtn.background = ContextCompat.getDrawable(requireActivity(), R.drawable.button_not_selected)
            mBinding.businessButton.background = ContextCompat.getDrawable(requireActivity(), R.drawable.button_not_selected)

            viewModel.resultCategory.observe(viewLifecycleOwner, Observer { response ->
                when (response) {
                    is com.nenad.newsapp.database.network.Response.Success -> {
                        hideProgressBar()
                        response.data.let {
                            categoryAdapter.differ.submitList(it?.articles)
                        }
                    }
                    is com.nenad.newsapp.database.network.Response.Error -> {
                        hideProgressBar()
                        response.message.let {
                            Log.e("TAG", "ERROR")

                        }

                    }
                }

            })
        }
        mBinding.techBtn.setOnClickListener {
            viewModel.getTechNews()
            mBinding.sportsBtn.background = ContextCompat.getDrawable(requireActivity(), R.drawable.button_not_selected)
            mBinding.entertainmentBtn.background = ContextCompat.getDrawable(requireActivity(), R.drawable.button_not_selected)
            mBinding.scienceBtn.background = ContextCompat.getDrawable(requireActivity(), R.drawable.button_not_selected)
            mBinding.techBtn.background = ContextCompat.getDrawable(requireActivity(), R.drawable.button_selected)
            mBinding.healthBtn.background = ContextCompat.getDrawable(requireActivity(), R.drawable.button_not_selected)
            mBinding.businessButton.background = ContextCompat.getDrawable(requireActivity(), R.drawable.button_not_selected)
            viewModel.resultCategory.observe(viewLifecycleOwner, Observer { response ->
                when (response) {
                    is com.nenad.newsapp.database.network.Response.Success -> {
                        hideProgressBar()
                        response.data.let {
                            categoryAdapter.differ.submitList(it?.articles)
                        }
                    }
                    is com.nenad.newsapp.database.network.Response.Error -> {
                        hideProgressBar()
                        response.message.let {
                            Log.e("TAG", "ERROR")

                        }

                    }
                }

            })
        }





    }

    private fun setUpRecyclerView() {
        topHeadlinesAdapter = TopHeadlinesAdapter(this)
        categoryAdapter = CategoryAdapter(this)
        mBinding.recyclerView.apply {
            adapter = topHeadlinesAdapter
            layoutManager = LinearLayoutManager(activity)
        }

        mBinding.rv2.apply {
            adapter = categoryAdapter
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        }


    }




}




