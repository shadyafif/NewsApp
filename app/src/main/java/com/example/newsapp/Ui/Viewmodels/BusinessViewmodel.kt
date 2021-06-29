package com.example.newsapp.Ui.Viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.newsapp.Repository.BusinessRepo

class BusinessViewmodel : ViewModel() {
    val articles = Pager(PagingConfig(pageSize = 20)) {
        BusinessRepo()
    }.flow.cachedIn(viewModelScope)
}