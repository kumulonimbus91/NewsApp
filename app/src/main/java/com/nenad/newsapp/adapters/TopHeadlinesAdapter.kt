package com.nenad.newsapp.adapters

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nenad.newsapp.database.model.apiresponse.Article
import com.nenad.newsapp.databinding.ListitemBinding
import com.nenad.newsapp.view.fragments.HomeFragment
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class TopHeadlinesAdapter(private val fragment: Fragment) : RecyclerView.Adapter<TopHeadlinesAdapter.ViewHolder>() {

    private val diffCallBack = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
           return oldItem == newItem
        }
    }

    var differ = AsyncListDiffer(this, diffCallBack)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

//        return ViewHolder (LayoutInflater.from(parent.context).inflate(R.layout.listitem, parent, false))


        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        val article = differ.currentList[position]


        holder.bind(article)

        holder.itemView.apply {
            Glide.with(this).load(article.urlToImage).into(holder.binding.img)
            holder.binding.source.text = article.source.name
            holder.binding.description.text = article.description
            holder.binding.titleTextView.text = article.title


            setOnClickListener {
              onItemClickListener?.let {
                  it(article)
              }
            }

        }









    }
    private var onItemClickListener: ((Article) -> Unit)? = null

    fun setOnClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener

    }

    override fun getItemCount(): Int {
        return differ.currentList.size

    }

    class ViewHolder private constructor(val binding: ListitemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)

                val binding = ListitemBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }

        fun bind(item: Article) {





        }



    }
    class OnClickListener(val clickListener: (article: Article) -> Unit) {
        fun onClick(article: Article) = clickListener(article)
    }










}