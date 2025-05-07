package com.example.booklist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.booklist.data.Datasource
import com.example.booklist.model.Book
import com.example.booklist.screens.BookDetailScreen
import com.example.booklist.screens.BookListScreen
import com.example.booklist.ui.theme.BookListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BookListTheme {
                BooksApp()
            }
        }
    }
}

@Composable
fun BooksApp() {
    val layoutDirection = LocalLayoutDirection.current
    val navController = rememberNavController()
    val books = Datasource().loadBooks()
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(
                start = WindowInsets.safeDrawing.asPaddingValues().calculateStartPadding(layoutDirection),
                end  = WindowInsets.safeDrawing.asPaddingValues().calculateEndPadding(layoutDirection),
            )
    ) {
        NavHost(navController = navController, startDestination = "ListScreen") {
            composable(route = "ListScreen") {
                BookListScreen(bookList = books, onDetailClick = {
                    index -> navController.navigate("detail/$index")
                })
            }
            composable("detail/{index}") { backStackEntry ->
                val indexString = backStackEntry.arguments?.getString("index")
                val index = indexString?.toIntOrNull()
                val book = index?.let { books.getOrNull(it) }

                if (book != null) {
                    BookDetailScreen(book = book)
                } else {
                    androidx.compose.material3.Text("Buku tidak ditemukan", modifier = Modifier.padding(16.dp))
                }
                }
            }
        }

    }
