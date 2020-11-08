package com.linux.dailyarticle.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linux.dailyarticle.api.response.article.ArticleResp
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
                val articleResp = repository.getArticle("20200909")
                _article.value = covertToArticle(articleResp)
            } catch (e: Exception) {
                errorMessage.value = e.message
            }
        }
    }

    private fun covertToArticle(resp: ArticleResp): Article? {
        val (author, content, date, digest, title, wc) = resp.data
        return Article(
            date = date.curr,
            author = author,
            content = content,
            digest = digest,
            title = title,
            words = wc.toString()
        )
    }
}