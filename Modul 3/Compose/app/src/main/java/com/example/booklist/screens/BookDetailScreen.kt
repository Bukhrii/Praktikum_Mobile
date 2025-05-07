package com.example.booklist.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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

@Composable
fun BookDetailScreen(book: Book) {
    val orientation = LocalConfiguration.current.orientation
    val isLandscape = orientation == Configuration.ORIENTATION_LANDSCAPE

    if (isLandscape) {
        Row(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = painterResource(book.imageResourceId),
                contentDescription = null,
                modifier = Modifier
                    .size(width = 220.dp, height = 350.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.Start
            ) {
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
                Spacer(modifier = Modifier.height(8.dp))
                Text("Tentang:", style = MaterialTheme.typography.bodySmall)
                Text(
                    text = LocalContext.current.getString(book.aboutResourceId),
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 14.sp
                )
            }
        }
    } else {
        Column(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = painterResource(book.imageResourceId),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Text(
                    text = LocalContext.current.getString(book.titleResourceId),
                    modifier = Modifier.weight(1f),
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