package com.linux.dailyarticle.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linux.dailyarticle.api.response.article.ArticleResp
import com.linux.dailyarticle.domain.entity.Article
import com.linux.dailyarticle.repository.ArticleRepository
import com.linux.dailyarticle.util.DateUtils
import kotlinx.coroutines.launch
import java.util.*

class HomeViewModel @ViewModelInject constructor(private val repository: ArticleRepository) : ViewModel() {

    private val _article = MutableLiveData<Article>()
    val article: LiveData<Article> get() = _article

    private val _date = MutableLiveData<Date>()
    val date: LiveData<Date> get() = _date


    private val errorMessage: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    init {
        getArticleByDate(null)
    }

    fun setDate(date: Date?) {
        _date.postValue(date)
        getArticleByDate(date)
    }

    private fun getArticleByDate(date: Date?) {
        val dateString = if (date == null) {
            DateUtils.currentArticle()
        } else {
            DateUtils.format(date, "yyyyMMdd")
        }
        _date.postValue(date ?: Date())
        viewModelScope.launch {
            try {
                val articleResp = repository.getArticle(dateString)
                _article.postValue(covertToArticle(articleResp))
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