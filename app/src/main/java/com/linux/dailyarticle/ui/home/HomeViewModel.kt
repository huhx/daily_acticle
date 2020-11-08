package com.linux.dailyarticle.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linux.dailyarticle.domain.entity.Article
import com.linux.dailyarticle.repository.ArticleRepository
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(private val repository: ArticleRepository) : ViewModel() {

    private val _article = MutableLiveData<Article>()

    val article: LiveData<Article> get() = _article

    private val errorMessage: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    init {
        viewModelScope.launch {
            try {
                _article.postValue(repository.getArticle("20201020"))
            } catch (e: Exception) {
                errorMessage.value = e.message
            }
        }
    }
}