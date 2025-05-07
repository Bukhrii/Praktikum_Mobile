package com.example.booklist.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.booklist.BooksApp
import com.example.booklist.model.Book

@Composable
fun BookListScreen(bookList: List<Book>, onDetailClick: (Int) -> Unit, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(bookList.indices.toList()) {
            index -> BookCard(
                book = bookList[index],
                onDetailClick = { onDetailClick(index) }
            )
        }
    }
}

@Composable
fun BookCard(book: Book, onDetailClick: () -> Unit, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        val context = LocalContext.current
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(book.imageResourceId),
                contentDescription = stringResource(book.aboutResourceId),
                modifier = Modifier
                    .size(width = 120.dp, height = 200.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .align(Alignment.CenterVertically),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = context.getString(book.titleResourceId),
                        modifier = Modifier.weight(1f),
                        style = MaterialTheme.typography.labelLarge,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 26.sp
                    )
                    Text(
                        text = context.getString(book.yearResourceId),
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 14.sp
                    )

                }
                Spacer(modifier = Modifier.height(20.dp))
                Row {
                    Text(
                        text = "Tentang: ",
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 14.sp
                    )
                    Text(
                        text = context.getString(book.aboutResourceId),
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 14.sp
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End) {
                    Button(onClick = {
                        val webUrl = context.getString(book.webUrlResourceId)
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(webUrl))
                        context.startActivity(intent)
                    }) {
                        Text("Beli Buku")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = {
                        onDetailClick()
                    }) {
                        Text("Detail")
                    }
                }

            }
        }

    }
}
