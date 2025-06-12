package com.example.movielist.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String?,
    val backdropPath: String?,
    val voteAverage: Double,
    val releaseDate: String?
) {
    fun getPosterUrl(): String {
        return if (posterPath != null && posterPath.isNotEmpty()) "https://image.tmdb.org/t/p/w500$posterPath" else ""
    }

    fun getBackdropUrl(): String {
        return if (backdropPath != null && backdropPath.isNotEmpty()) "https://image.tmdb.org/t/p/w780$backdropPath" else ""
    }

    fun getTmdbUrl(): String {
        return "https://www.themoviedb.org/movie/$id"
    }
}