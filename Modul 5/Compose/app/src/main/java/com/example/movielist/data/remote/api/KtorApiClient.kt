package com.example.movielist.data.remote.api

import com.example.movielist.data.remote.model.MovieResponseDto
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object KtorApiClient {
    val client = HttpClient(Android) {
        install(Logging) {
            level = LogLevel.ALL
        }
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
                prettyPrint = true
            })
        }
    }

    suspend fun getPopularMovies(): MovieResponseDto {
        return client.get(ApiConstants.BASE_URL + "movie/popular") {
            parameter("api_key", ApiConstants.API_KEY)
        }.body()
    }
}