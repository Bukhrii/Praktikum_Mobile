package com.example.movielist

import android.content.Context
import com.example.movielist.data.local.AppDatabase
import com.example.movielist.data.remote.api.KtorApiClient
import com.example.movielist.data.repository.MovieRepositoryImpl
import com.example.movielist.domain.repository.MovieRepository
import com.example.movielist.domain.usecase.GetMoviesUseCase
import com.example.movielist.presentation.ui.screens.list.MovieListViewModelFactory
import com.example.movielist.util.SettingsManager

class AppContainer(private val context: Context) {

    private val appDatabase: AppDatabase by lazy {
        AppDatabase.create(context)
    }

    private val movieDao by lazy {
        appDatabase.movieDao()
    }

    private val ktorApiClient: KtorApiClient by lazy {
        KtorApiClient
    }

    private val movieRepository: MovieRepository by lazy {
        MovieRepositoryImpl(ktorApiClient, movieDao)
    }

    private val getMoviesUseCase: GetMoviesUseCase by lazy {
        GetMoviesUseCase(movieRepository)
    }

    val movieListViewModelFactory: MovieListViewModelFactory by lazy {
        MovieListViewModelFactory(getMoviesUseCase)
    }

    val settingsManager: SettingsManager by lazy {
        SettingsManager(context)
    }
}