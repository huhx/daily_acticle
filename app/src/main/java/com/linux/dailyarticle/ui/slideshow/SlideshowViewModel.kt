package com.linux.dailyarticle.ui.slideshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SlideshowViewModel : ViewModel() {

    val text: LiveData<String> = MutableLiveData("This is slideshow Fragment")
}