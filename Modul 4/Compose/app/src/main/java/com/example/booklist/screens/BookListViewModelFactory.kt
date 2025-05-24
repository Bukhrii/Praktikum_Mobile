package com.example.booklist.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class BookListViewModelFactory(private val source: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom((BookListViewModel::class.java))) {
            @Suppress("UNCHECKED_CAST")
            return BookListViewModel(source) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}