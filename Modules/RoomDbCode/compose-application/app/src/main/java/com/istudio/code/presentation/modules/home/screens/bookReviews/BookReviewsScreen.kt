package com.istudio.code.presentation.modules.home.screens.bookReviews

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.istudio.code.presentation.modules.home.HomeVm

@Composable
fun ScreenBookReviews(viewModelStore: ViewModelStoreOwner) {
    CurrentScreen(viewModelStore)
}

@Preview
@Composable
private fun CurrentScreenPreview() {
    val viewModelStore = checkNotNull(LocalViewModelStoreOwner.current)
    CurrentScreen(viewModelStore)
}


@Composable
private fun CurrentScreen(viewModelStore: ViewModelStoreOwner) {
    val viewModel = viewModel<HomeVm>(viewModelStoreOwner = viewModelStore)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.DarkGray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.Monospace,
            style = TextStyle(
                color = Color.White
            ),
            text = "BookReviews"
        )
    }
}