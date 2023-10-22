package com.istudio.code.presentation.modules.home.screens.bookReviews

import android.text.Html
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.istudio.code.R
import com.istudio.code.core.platform.utils.composeUtils.HtmlText
import com.istudio.code.core.platform.utils.formatDateToText
import com.istudio.code.domain.database.models.relations.ReviewAndBook
import org.mockito.kotlin.mock

@Composable
fun MyReviewsItem(
    item: ReviewAndBook, onClick:() -> Unit
) {
    MyReview(item,onClick)
}

@Preview
@Composable
private fun CurrentScreenPreview() {
    MyReview(
        item = mock()
    ){}
}

@Composable
fun MyReview(
    item: ReviewAndBook,
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
        val nameTag = cxt.getString(R.string.str_name_tag)
        val ratingTag = cxt.getString(R.string.str_rating_tag)
        val updatedDateTag = cxt.getString(R.string.str_updated_date_tag)
        val reviewTag = cxt.getString(R.string.str_review_notes_tag)

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = nameTag, style = MaterialTheme.typography.titleLarge
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
                    text = ratingTag, style = MaterialTheme.typography.titleMedium
                )
                Text(
                    item.review.rating.toString(), style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Column(
                horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = reviewTag, style = MaterialTheme.typography.titleSmall
                )
                Text(
                    text = item.review.notes,
                    style = MaterialTheme.typography.bodySmall, maxLines = 5
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Column(
                horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = updatedDateTag, style = MaterialTheme.typography.titleMedium
                )
                Text(
                    formatDateToText(item.review.lastUpdatedDate), style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
