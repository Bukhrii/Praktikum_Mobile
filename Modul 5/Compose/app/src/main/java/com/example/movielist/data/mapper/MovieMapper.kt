package com.example.movielist.data.mapper

import com.example.movielist.data.local.model.MovieEntity
import com.example.movielist.data.remote.model.MovieResultDto
import com.example.movielist.domain.model.Movie

fun MovieResultDto.toEntity(): MovieEntity {
    return MovieEntity(
        id = id ?: 0,
        title = title.orEmpty(),
        overview = overview.orEmpty(),
        posterPath = posterPath.orEmpty(),
        backdropPath = backdropPath.orEmpty(),
        releaseDate = releaseDate.orEmpty(),
        voteAverage = voteAverage ?: 0.0,
        popularity = popularity ?: 0.0
    )
}

fun MovieEntity.toDomain(): Movie {
    return Movie(
        id = this.id,
        title = this.title,
        overview = this.overview,
        posterPath = this.posterPath,
        backdropPath = this.backdropPath,
        releaseDate = this.releaseDate,
        voteAverage = this.voteAverage
    )
}