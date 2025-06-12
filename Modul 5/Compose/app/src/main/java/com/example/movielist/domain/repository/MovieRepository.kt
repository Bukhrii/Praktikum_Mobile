package com.example.movielist.domain.repository

import com.example.movielist.core.common.NetworkResult
import com.example.movielist.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovies(): Flow<List<Movie>>
    suspend fun refreshMovies(): NetworkResult<Unit>
}