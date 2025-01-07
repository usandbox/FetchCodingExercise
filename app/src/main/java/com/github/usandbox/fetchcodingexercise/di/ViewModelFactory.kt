package com.github.usandbox.fetchcodingexercise.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.usandbox.fetchcodingexercise.data.FetchRepository
import com.github.usandbox.fetchcodingexercise.ui.MainViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    private val repository: FetchRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
