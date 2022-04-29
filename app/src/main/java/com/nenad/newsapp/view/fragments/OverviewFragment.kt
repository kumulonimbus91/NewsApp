package com.nenad.newsapp.view.fragments

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.nenad.newsapp.R
import com.nenad.newsapp.adapters.SavedNewsAdapter
import com.nenad.newsapp.database.model.apiresponse.Article
import com.nenad.newsapp.databinding.FragmentOverViewBinding
import com.nenad.newsapp.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class OverviewFragment : Fragment() {

    private lateinit var mBinding: FragmentOverViewBinding

//    private val viewModel: MainViewModel by viewModels()

    val args: OverviewFragmentArgs by navArgs()

    val viewModel: MainViewModel by activityViewModels<MainViewModel>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_over_view, container, false)

        val article = OverviewFragmentArgs.fromBundle(requireArguments())

        Glide.with(this).load(article.article.urlToImage).into(mBinding.imgOverview)
        mBinding.text.text = article.article.title
        mBinding.textnews.text = article.article.content
        mBinding.srctext.text = article.article.author


        mBinding.textnews.movementMethod





        mBinding.lifecycleOwner = this

        mBinding.favoriteBtn.setOnClickListener {
            Toast.makeText(requireContext(), "Article saved", Toast.LENGTH_SHORT).show()
            viewModel.insertArticle(args.article)
        }



        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)





    }



}