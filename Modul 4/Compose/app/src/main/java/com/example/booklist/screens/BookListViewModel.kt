package com.example.booklist.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booklist.data.Datasource
import com.example.booklist.model.Book
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class BookListViewModel(private val source: String) : ViewModel() {
    private val _booksList = MutableStateFlow<List<Book>>(emptyList())
    val booksList: StateFlow<List<Book>> = _booksList

    private val _selectedBook = MutableStateFlow<Book?>(null)
    val selectedBook: StateFlow<Book?> = _selectedBook

    init {
        Timber.d("Sumber data ViewModel: $source")
        loadBooks()
    }

    private fun loadBooks() {
        viewModelScope.launch {
            val books = Datasource().loadBooks()
            _booksList.value = books
            Timber.d("Data buku berhasil dimuat: ${books.size} item")
        }
    }

    fun selectBook(book: Book) {
        _selectedBook.value = book
        Timber.d("Buku yang dipilih: ${book.titleResourceId}")
    }
}