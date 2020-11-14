package com.linux.dailyarticle.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linux.dailyarticle.domain.entity.Article
import com.linux.dailyarticle.repository.ArticleRepository
import com.linux.dailyarticle.util.DateUtils
import kotlinx.coroutines.launch
import java.util.*

class HomeViewModel @ViewModelInject constructor(private val repository: ArticleRepository) : ViewModel() {

    val article: MutableLiveData<Article> = MutableLiveData()
    val isMarked: MutableLiveData<Boolean> = MutableLiveData(false)
    val currentDate: MutableLiveData<Date> = MutableLiveData(Date())

    private val errorMessage: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    init {
        getArticleByDate(null)
    }

    fun setDate(date: Date?) {
        MutableLiveData<Date>().postValue(date)
        getArticleByDate(date)
    }

    private fun getArticleByDate(date: Date?) {
        val dateString = if (date == null) DateUtils.currentArticle() else DateUtils.dateFormat(date)
        currentDate.value = date ?: Date()
        viewModelScope.launch {
            try {
                article.postValue(repository.getArticle(dateString))
                val favoriteArticle = repository.queryFavorite(DateUtils.dateFormat(currentDate.value))
                isMarked.postValue(favoriteArticle?.status)
            } catch (e: Exception) {
                errorMessage.value = e.message
            }
        }
    }

    fun updateFavoriteArticle() {
    }
}