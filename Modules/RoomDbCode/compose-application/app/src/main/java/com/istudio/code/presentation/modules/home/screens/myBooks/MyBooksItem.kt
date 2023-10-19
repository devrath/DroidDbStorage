package com.istudio.code.presentation.modules.home.screens.myBooks

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.istudio.code.domain.database.models.Book
import com.istudio.code.domain.database.models.Genre
import com.istudio.code.domain.database.models.relations.BookAndGenre

@Composable
fun MyBooksItem(
    item: BookAndGenre, onClick:() -> Unit
) {
    MyBook(item,onClick)
}


@Preview
@Composable
private fun CurrentScreenPreview() {
    MyBook(
        BookAndGenre(
            book = Book(
                name = "BookName",
                description = "Description",
                genreId = "123",
            ), genre = Genre(
                name = "Action Packed"
            )
        )
    ){

    }
}

@Composable
fun MyBook(
    item: BookAndGenre,
    onClick:() -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable(onClick = { onClick }),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                item.book.name, style = MaterialTheme.typography.titleLarge,
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                item.genre.name, style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                item.book.description, style = MaterialTheme.typography.bodySmall,
                maxLines = 5
            )
        }
    }
}

