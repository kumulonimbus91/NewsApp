package com.nenad.newsapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nenad.newsapp.database.model.apiresponse.Article
import com.nenad.newsapp.databinding.ListitemsavedBinding


class SavedNewsAdapter() :
    RecyclerView.Adapter<SavedNewsAdapter.ViewHolder>() {

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
            holder.binding.titleTextView.text = article.title
            holder.binding.source.text = article.source.name


            setOnClickListener {
                onItemClickListener?.let {
                    (article)
                    it.onEdit(article)
                }
            }
            holder.binding.clearbutton.setOnClickListener {
                 onItemClickListener?.let {
                     it.onDelete(article)
                 }
            }


        }


    }

    private var onItemClickListener: (MyClickListener)? = null

    fun setOnClickListener(listener: MyClickListener) {
        onItemClickListener = listener

    }

    override fun getItemCount(): Int {
        return differ.currentList.size

    }

    class ViewHolder  constructor(
        val binding: ListitemsavedBinding
    ) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {




        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)

                val binding = ListitemsavedBinding.inflate(layoutInflater, parent, false)




                return ViewHolder(binding)
            }
        }

        fun bind(item: Article) {




        }



        override fun onClick(p0: View?) {

        }


    }

    interface MyClickListener: AdapterView.OnItemClickListener {
        fun onEdit(p: Article)
        fun onDelete(p: Article)
    }



}