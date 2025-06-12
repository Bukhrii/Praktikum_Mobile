package com.example.movielist.presentation.ui.navigation

sealed class Screen(val route: String) {
    data object MovieList : Screen("movie_list")
    data object MovieDetail : Screen("movie_detail")
}