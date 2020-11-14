package com.linux.dailyarticle.ui.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GalleryViewModel : ViewModel() {

    val text: LiveData<String> = MutableLiveData("This is gallery Fragment")
}