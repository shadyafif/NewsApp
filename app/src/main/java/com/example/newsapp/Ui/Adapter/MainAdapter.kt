package com.example.newsapp.Ui.Adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.Data.Model.Article
import com.example.newsapp.R
import com.example.newsapp.Utlies.Helper.loadImage
import com.example.newsapp.databinding.LayoutMainRecyclerBinding


class MainAdapter(private val onClick: OnNewsItemClick, val context: Context) :
    PagingDataAdapter<Article, MainAdapter.MainViewHolder>(ArticelComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            LayoutMainRecyclerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
        holder.Init(currentItem!!, onClick)
    }

    inner class MainViewHolder(private val binding: LayoutMainRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun Init(Items: Article, action: OnNewsItemClick) {
            itemView.setOnClickListener {
                action.onItemClick(Items, layoutPosition)
            }

        }

        fun bind(article: Article?) {
            binding.apply {
                tvTitle.text = article!!.title
                tvSource.text = article.source?.name
                if (article.urlToImage != null) {
                    ivImage.loadImage(article.urlToImage)
                } else {
                    ivImage.setImageResource(R.drawable.nophoto)
                }
            }
        }

    }

    interface OnNewsItemClick {
        fun onItemClick(NewsItems: Article, position: Int)
    }

    companion object {

        private val ArticelComparator = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean =
                oldItem.title == newItem.title

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean =
                oldItem == newItem
        }
    }

}
