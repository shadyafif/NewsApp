package com.example.newsapp.Ui.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.Data.Model.Article
import com.example.newsapp.Data.Room.ArticleDatabase
import com.example.newsapp.R
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FavoriteAdapter(val context: Context, var click: FavoriteAdapter.OnNewsItemClick) :
    RecyclerView.Adapter<FavoriteAdapter.Viewholder>() {

    lateinit var NewsList: List<Article>


    fun setFavNewsList(favMoviesList: List<Article>) {
        NewsList = favMoviesList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.favorite_layout, parent, false)
        return Viewholder(itemView)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        var currentItem = NewsList[position]
        holder.tv_title.text = currentItem?.title
        holder.tv_source.text = currentItem?.source?.name

        if (currentItem?.urlToImage == "") {
            Picasso.get()
                .load(currentItem?.urlToImage)
                .resize(400, 400)
                .placeholder(R.mipmap.ic_nophoto)
                .into(holder.iv_image)
        } else {
            Picasso.get()
                .load(currentItem?.urlToImage)
                .resize(400, 400)
                .placeholder(R.drawable.square)
                .into(holder.iv_image)
        }

        holder.iv_Delete.setOnClickListener(View.OnClickListener { view ->
            GlobalScope.launch(Dispatchers.IO)
            {
                ArticleDatabase(context).getArticleDao().deleteArticle(currentItem)
                val Message: String = context?.getString(R.string.checkNotChecked)
                Snackbar.make(view, Message, Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show()
            }
        })

        holder.Init(currentItem, click)
    }

    override fun getItemCount(): Int {
        return NewsList.size
    }

    class Viewholder(view: View) : RecyclerView.ViewHolder(view) {
        var tv_title: TextView = view.findViewById(R.id.tv_title)
        var tv_source: TextView = view.findViewById(R.id.tv_source)
        var iv_image: ImageView = view.findViewById(R.id.iv_image)
        var iv_Delete: ImageView = view.findViewById(R.id.iv_Delete)

        fun Init(Items: Article, action: FavoriteAdapter.OnNewsItemClick) {
            itemView.setOnClickListener {
                action.onItemClick(Items, layoutPosition)
            }

        }
    }

    interface OnNewsItemClick {
        fun onItemClick(NewsItems: Article, position: Int)
    }
}