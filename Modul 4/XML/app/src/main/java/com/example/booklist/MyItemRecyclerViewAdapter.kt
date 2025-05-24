package com.example.booklist

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.booklist.databinding.ItemBookBinding
import timber.log.Timber

class BookAdapter(
    private val context: Context,
    private val books: List<Book>,
    private val onAboutClick: (Book) -> Unit
) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    inner class BookViewHolder(val binding: ItemBookBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = ItemBookBinding.inflate(LayoutInflater.from(context), parent, false)
        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = books[position]
        with(holder.binding) {
            bookTitle.text = context.getString(book.titleResourceId)
            bookYear.text = context.getString(book.yearResourceId)
            bookAbout.text = context.getString(book.aboutResourceId)
            bookImage.setImageResource(book.imageResourceId)

            btnBuy.setOnClickListener {
                Timber.d("Tombol 'Beli' ditekan untuk URL ID: ${book.webUrlResourceId}")
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(context.getString(book.webUrlResourceId)))
                context.startActivity(intent)
            }

            btnAbout.setOnClickListener {
                Timber.d("Tombol 'Tentang' ditekan untuk buku: ${context.getString(book.titleResourceId)}")
                onAboutClick(book)
            }
        }
    }

    override fun getItemCount() = books.size
}