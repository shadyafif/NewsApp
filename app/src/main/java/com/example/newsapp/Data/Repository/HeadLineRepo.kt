package com.example.newsapp.Data.Repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newsapp.Data.Model.Article
import com.example.newsapp.Data.Network.RetrofitClient
import com.example.newsapp.Utlies.Constants

class HeadLineRepo()
    : PagingSource<Int, Article>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = RetrofitClient.getApi.getHeadlines(Constants.API_KEY,"en",nextPageNumber)
            LoadResult.Page(
                data = response.articles,
                prevKey = if (nextPageNumber > 0) nextPageNumber - 1 else null,
                nextKey = if (nextPageNumber < response.totalResults) nextPageNumber + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int {
        return state.anchorPosition!!
    }
}