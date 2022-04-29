package com.nenad.newsapp.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.nenad.newsapp.R
import com.nenad.newsapp.adapters.SavedNewsAdapter
import com.nenad.newsapp.database.model.apiresponse.Article
import com.nenad.newsapp.databinding.FragmentSavedNewsBinding
import com.nenad.newsapp.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SavedNewsFragment : Fragment(), View.OnClickListener {
    private lateinit var mBinding: FragmentSavedNewsBinding
    lateinit var savedNewsAdapter: SavedNewsAdapter

//    val args: SavedNewsFragmentArgs by navArgs()

    val viewModel: MainViewModel by activityViewModels<MainViewModel>()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)





    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_saved_news, container, false)
        mBinding.lifecycleOwner = this


















        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        setUpRecyclerView()
        setUpClickListener()


        lifecycleScope.launch {

        }

        viewModel.readArt.observe(viewLifecycleOwner, Observer { articles ->
            savedNewsAdapter.differ.submitList(articles)
        })

        mBinding.clearAllBtn.setOnClickListener {
            viewModel.deleteAllSavedArticles()
        }










    }



    private fun setUpRecyclerView() {
        savedNewsAdapter = SavedNewsAdapter()
        mBinding.recyclerView.apply {
            adapter = savedNewsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun setUpClickListener() {
        savedNewsAdapter.setOnClickListener ( object : SavedNewsAdapter.MyClickListener {
            override fun onEdit(p: Article) {


                         val action = SavedNewsFragmentDirections.actionSavedNewsFragmentToOverviewFragment(p)
                         findNavController().navigate(action)
            }

            override fun onDelete(p: Article) {
                viewModel.deleteArticle(p)
            }

            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

            }


//         val action = SavedNewsFragmentDirections.actionSavedNewsFragmentToOverviewFragment(it)
//            findNavController().navigate(action)
        })




    }

    override fun onClick(p0: View?) {
        when(p0) {


            }
        }

    }



