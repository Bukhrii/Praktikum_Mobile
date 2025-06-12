package com.example.movielist.domain.usecase

import com.example.movielist.core.common.NetworkResult
import com.example.movielist.domain.model.Movie
import com.example.movielist.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetMoviesUseCase(private val movieRepository: MovieRepository) {
    fun getMoviesFromDb(): Flow<List<Movie>> = movieRepository.getMovies()

    suspend fun refreshMoviesFromApi(): NetworkResult<Unit> = movieRepository.refreshMovies()
}