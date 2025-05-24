package com.example.booklist.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.booklist.model.Book
import timber.log.Timber

@Composable
fun BookDetailScreen(book: Book) {
    Timber.i("Detail buku ditampilkan: ${LocalContext.current.getString(book.titleResourceId)}")
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Image(
                painter = painterResource(book.imageResourceId),
                contentDescription = null,
                modifier = Modifier
                    .size(width = 380.dp, height = 550.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .align(Alignment.CenterHorizontally),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = LocalContext.current.getString(book.titleResourceId),
                        style = MaterialTheme.typography.labelLarge,
                        fontSize = 26.sp
                    )
                    Text(
                        text = LocalContext.current.getString(book.yearResourceId),
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 20.sp
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text("Tentang:", style = MaterialTheme.typography.bodySmall)
                Text(
                    text = LocalContext.current.getString(book.aboutResourceId),
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 14.sp
                )
            }
            }
    }