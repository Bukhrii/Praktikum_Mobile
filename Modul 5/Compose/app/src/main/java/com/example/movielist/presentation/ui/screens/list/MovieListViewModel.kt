package com.example.movielist.presentation.ui.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movielist.core.common.NetworkResult
import com.example.movielist.domain.model.Movie
import com.example.movielist.domain.usecase.GetMoviesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

data class MovieListUiState(
    val movies: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

class MovieListViewModel(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(MovieListUiState())
    val uiState: StateFlow<MovieListUiState> = _uiState.asStateFlow()

    init {
        observeMovies()
        refreshData()
    }

    private fun observeMovies() {
        viewModelScope.launch {
            getMoviesUseCase.getMoviesFromDb()
                .onStart { _uiState.value = _uiState.value.copy(isLoading = true) }
                .catch { e ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = "Gagal memuat data dari database: ${e.message}"
                    )
                }
                .collect { movies ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        movies = movies
                    )
                }
        }
    }

    fun refreshData() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
            when (val result = getMoviesUseCase.refreshMoviesFromApi()) {
                is NetworkResult.Success -> {
                    _uiState.value = _uiState.value.copy(isLoading = false)
                }
                is NetworkResult.Error -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = result.message
                    )
                }
                else -> {}
            }
        }
    }
}