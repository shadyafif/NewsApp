package com.example.newsapp.Data.Room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.newsapp.Data.Model.Article


@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addArticle(article: Article)

    @Query("SELECT * FROM articles")
    fun getAllArticles(): LiveData<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)

    @Query("SELECT * FROM articles where title = :name")
    fun fetchCart(name: String?): Article?
}