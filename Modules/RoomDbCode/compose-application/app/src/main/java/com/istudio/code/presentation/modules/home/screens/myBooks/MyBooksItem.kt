package com.istudio.code.presentation.modules.home.screens.myBooks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MyBooksItem(title: String, description: String) {
    MyBook(title, description)
}


@Preview
@Composable
private fun CurrentScreenPreview() {
    MyBook(title = "Title", description = "Description is some sentences")
}

@Composable
fun MyBook(title: String, description: String) {
    val paddingModifier = Modifier.padding(10.dp)
    Card(modifier = paddingModifier) {
        Column(
            modifier = paddingModifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                title, style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                description, style = MaterialTheme.typography.bodyMedium
            )
        }
    }

}
