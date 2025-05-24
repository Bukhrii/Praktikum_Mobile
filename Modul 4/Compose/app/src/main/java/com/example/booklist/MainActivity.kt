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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.booklist.screens.BookDetailScreen
import com.example.booklist.screens.BookListScreen
import com.example.booklist.screens.BookListViewModel
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
    val viewModel: BookListViewModel = viewModel()
    val bookList by viewModel.booksList.collectAsState()
    val selectedBook by viewModel.selectedBook.collectAsState()

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(
                start = WindowInsets.safeDrawing.asPaddingValues().calculateStartPadding(layoutDirection),
                end  = WindowInsets.safeDrawing.asPaddingValues().calculateEndPadding(layoutDirection),
            )
    ) {
        NavHost(navController = navController, startDestination = "list") {
            composable("list") {
                BookListScreen(bookList = bookList, onDetailClick = { index ->
                    viewModel.selectBook(bookList[index])
                    navController.navigate("detail")
                })
            }
            composable("detail") { backStackEntry ->
                if (selectedBook != null) {
                    BookDetailScreen(book = selectedBook!!)
                } else {
                    Text("Buku tidak ditemukan", modifier = Modifier.padding(16.dp))
                }
                }
            }
        }

    }
