package com.example.movielist.presentation.ui.screens.list

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movielist.presentation.ui.component.MovieCard
import com.example.movielist.util.LayoutType
import com.example.movielist.util.SettingsManager
import com.google.gson.Gson
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListScreen(
    viewModel: MovieListViewModel,
    settingsManager: SettingsManager,
    navController: NavController
) {
    val uiState by viewModel.uiState.collectAsState()
    val layoutType by settingsManager.layoutType.collectAsState(initial = LayoutType.LIST)
    val isDarkMode by settingsManager.isDarkMode.collectAsState(initial = false)
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Popular Movies") },
                actions = {
                    IconButton(onClick = {
                        scope.launch {
                            settingsManager.toggleDarkMode()
                        }
                    }) {
                        Icon(
                            imageVector = if (isDarkMode) Icons.Filled.CheckCircle else Icons.Filled.CheckCircle,
                            contentDescription = "Toggle Dark/Light Mode"
                        )
                    }

                    IconButton(onClick = {
                        scope.launch {
                            val newLayout = if (layoutType == LayoutType.LIST) LayoutType.GRID else LayoutType.LIST
                            settingsManager.setLayoutType(newLayout)
                        }
                    }) {
                        Icon(
                            imageVector = if (layoutType == LayoutType.LIST) Icons.Filled.Menu else Icons.AutoMirrored.Filled.List,
                            contentDescription = "Change Layout"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (uiState.isLoading && uiState.movies.isEmpty()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else if (uiState.errorMessage != null && uiState.movies.isEmpty()) {
                Text(
                    text = uiState.errorMessage ?: "Unknown Error",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                if (layoutType == LayoutType.LIST) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(uiState.movies) { movie ->
                            MovieCard(
                                movie = movie,
                                modifier = Modifier.fillMaxWidth(),
                                onMovieClick = { selectedMovie ->
                                    val movieJson = Uri.encode(Gson().toJson(selectedMovie))
                                    navController.navigate("movie_detail/$movieJson")
                                },
                                onTmdbClick = { tmdbUrl ->
                                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(tmdbUrl))
                                    context.startActivity(intent)
                                }
                            )
                        }
                    }
                } else {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(uiState.movies) { movie ->
                            MovieCard(
                                movie = movie,
                                onMovieClick = { selectedMovie ->
                                    val movieJson = Uri.encode(Gson().toJson(selectedMovie))
                                    navController.navigate("movie_detail/$movieJson")
                                },
                                onTmdbClick = { tmdbUrl ->
                                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(tmdbUrl))
                                    context.startActivity(intent)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}