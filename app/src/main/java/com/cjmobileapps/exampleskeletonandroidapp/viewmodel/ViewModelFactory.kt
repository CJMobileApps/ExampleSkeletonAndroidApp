package com.cjmobileapps.exampleskeletonandroidapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cjmobileapps.exampleskeletonandroidapp.MainService

class ViewModelFactory(private val mainService: MainService): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if(modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(mainService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
