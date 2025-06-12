package com.example.movielist.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.NavType
import com.example.movielist.AppContainer
import com.example.movielist.domain.model.Movie
import com.example.movielist.presentation.ui.screens.detail.MovieDetailScreen
import com.example.movielist.presentation.ui.screens.list.MovieListScreen
import com.example.movielist.presentation.ui.screens.list.MovieListViewModel
import com.example.movielist.ui.theme.MovieListTheme
import com.google.gson.Gson
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val appContainer = AppContainer(applicationContext)

        setContent {
            val settingsManager = appContainer.settingsManager
            val isSystemDark = isSystemInDarkTheme()
            val isDarkMode by settingsManager.isDarkMode.collectAsState(initial = isSystemDark)

            MovieListTheme(darkTheme = isDarkMode) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val movieListViewModel: MovieListViewModel = viewModel(
                        factory = appContainer.movieListViewModelFactory
                    )

                    NavHost(navController = navController, startDestination = "movie_list") {
                        composable("movie_list") {
                            MovieListScreen(
                                viewModel = movieListViewModel,
                                settingsManager = settingsManager,
                                navController = navController
                            )
                        }
                        composable(
                            route = "movie_detail/{movieJson}",
                            arguments = listOf(navArgument("movieJson") { type = NavType.StringType })
                        ) { backStackEntry ->
                            val movieJson = backStackEntry.arguments?.getString("movieJson")
                            val movie = Gson().fromJson(movieJson, Movie::class.java)
                            movie?.let {
                                MovieDetailScreen(movie = it)
                            } ?: run {
                                Text("Error: Movie details not available.", color = MaterialTheme.colorScheme.error)
                            }
                        }
                    }
                }
            }
        }
    }
}