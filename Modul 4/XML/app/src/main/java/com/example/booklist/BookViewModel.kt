package com.example.booklist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber

class BookViewModel(category: String) : ViewModel() {

    private val _books = MutableStateFlow<List<Book>>(emptyList())
    val books: StateFlow<List<Book>> = _books

    init {
        loadBooks()
    }


    private fun loadBooks() {
        _books.value = listOf(
            Book(R.string.book_title1, R.string.book_year1, R.string.book_about1, R.string.book_webUrl1, R.drawable.image1),
            Book(R.string.book_title2, R.string.book_year2, R.string.book_about2, R.string.book_webUrl2, R.drawable.image2),
            Book(R.string.book_title3, R.string.book_year3, R.string.book_about3, R.string.book_webUrl3, R.drawable.image3),
            Book(R.string.book_title4, R.string.book_year4, R.string.book_about4, R.string.book_webUrl4, R.drawable.image4),
            Book(R.string.book_title5, R.string.book_year5, R.string.book_about5, R.string.book_webUrl5, R.drawable.image5),
            Book(R.string.book_title6, R.string.book_year6, R.string.book_about6, R.string.book_webUrl6, R.drawable.image6),
            Book(R.string.book_title7, R.string.book_year7, R.string.book_about7, R.string.book_webUrl7, R.drawable.image7),
            Book(R.string.book_title8, R.string.book_year8, R.string.book_about8, R.string.book_webUrl8, R.drawable.image8)
        )
        Timber.i("Data buku berhasil dimuat: ${_books.value.size}")
    }
}

class BookViewModelFactory(private val category: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BookViewModel(category) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
