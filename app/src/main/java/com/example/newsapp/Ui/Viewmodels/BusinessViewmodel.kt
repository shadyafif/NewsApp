package com.example.newsapp.Ui.Viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.newsapp.Data.Repository.BusinessRepo
import com.example.newsapp.Data.Repository.HeadLineRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BusinessViewmodel @Inject constructor(private val repo: BusinessRepo) : ViewModel() {
    val articles = Pager(PagingConfig(pageSize = 20)) {
        repo
    }.flow.cachedIn(viewModelScope)
}