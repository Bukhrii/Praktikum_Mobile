package com.example.movielist.data.repository

import com.example.movielist.core.common.NetworkResult
import com.example.movielist.data.local.dao.MovieDao
import com.example.movielist.data.mapper.toDomain
import com.example.movielist.data.mapper.toEntity
import com.example.movielist.data.remote.api.KtorApiClient
import com.example.movielist.data.remote.model.MovieResponseDto
import com.example.movielist.domain.model.Movie
import com.example.movielist.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.io.IOException

class MovieRepositoryImpl(
    private val apiClient: KtorApiClient,
    private val movieDao: MovieDao
) : MovieRepository {

    override fun getMovies(): Flow<List<Movie>> {
        return movieDao.getMovies().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun refreshMovies(): NetworkResult<Unit> {
        return try {
            val response: MovieResponseDto = apiClient.getPopularMovies()
            val movieEntities = response.results?.map { it.toEntity() } ?: emptyList()
            movieDao.clearAndInsertMovies(movieEntities)
            NetworkResult.Success(Unit)
        } catch (e: IOException) {
            NetworkResult.Error("Gagal menyambung ke server. Periksa koneksi internet Anda.")
        } catch (e: Exception) {
            NetworkResult.Error("Terjadi error: ${e.message}")
        }
    }
}