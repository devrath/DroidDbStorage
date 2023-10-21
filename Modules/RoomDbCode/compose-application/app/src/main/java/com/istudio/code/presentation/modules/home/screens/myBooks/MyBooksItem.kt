package com.istudio.code.presentation.modules.home.screens.myBooks

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.istudio.code.R
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
            .clickable(onClick = { onClick.invoke() }),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    ) {

        val cxt = LocalContext.current
        val titleTag = cxt.getString(R.string.str_title_tag)
        val genreTag = cxt.getString(R.string.str_genre_tag)
        val descriptionTag = cxt.getString(R.string.str_description_tag)

        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = titleTag, style = MaterialTheme.typography.titleLarge
                )
                Text(
                    item.book.name, style = MaterialTheme.typography.bodyLarge
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Column(
                horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = genreTag, style = MaterialTheme.typography.titleMedium
                )
                Text(
                    item.genre.name, style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Column(
                horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = descriptionTag, style = MaterialTheme.typography.titleSmall
                )
                Text(
                    item.book.description, style = MaterialTheme.typography.bodySmall, maxLines = 5
                )
            }
        }
    }
}

