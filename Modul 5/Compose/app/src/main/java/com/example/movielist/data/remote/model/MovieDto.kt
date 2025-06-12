package com.example.movielist.data.remote.model

import android.annotation.SuppressLint
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class MovieResponseDto(
    val page: Int?,
    val results: List<MovieResultDto>?,
    @SerialName("total_pages")
    val totalPages: Int?,
    @SerialName("total_results")
    val totalResults: Int?
)

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class MovieResultDto(
    val id: Int?,
    val title: String?,
    val overview: String?,
    @SerialName("poster_path")
    val posterPath: String?,
    @SerialName("release_date")
    val releaseDate: String?,
    @SerialName("vote_average")
    val voteAverage: Double?,
    @SerialName("backdrop_path")
    val backdropPath: String?,
    val popularity: Double?
)