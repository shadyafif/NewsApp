package com.example.newsapp.Ui.Viewmodels

import android.app.Application
import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.Data.Model.Article
import com.example.newsapp.Data.Room.ArticleDatabase


class FavoriteViewModel(application: Application) : AndroidViewModel(application) {

    lateinit var NewsFavList: LiveData<List<Article>>

    fun getNews(): LiveData<List<Article>> {

        NewsFavList = ArticleDatabase(getApplication()).getArticleDao().getAllArticles()
        return NewsFavList

    }
}