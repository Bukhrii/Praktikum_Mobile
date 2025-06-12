package com.example.movielist.presentation.ui.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movielist.domain.usecase.GetMoviesUseCase

class MovieListViewModelFactory(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MovieListViewModel(getMoviesUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}