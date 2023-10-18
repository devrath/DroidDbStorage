package com.istudio.code.presentation.modules.home.screens.myBooks

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.istudio.code.presentation.modules.addbook.states.AddBookViewEvent
import com.istudio.code.presentation.modules.home.HomeVm
import com.istudio.code.presentation.modules.home.states.HomeUiEvent

@Composable
fun ScreenMyBooks(viewModelStore: ViewModelStoreOwner) {
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
    //viewModel.onEvent(AddBookViewEvent.SetIsDescriptionError(false))


    // <!------------ MAIN-COMPOSE-CONTROL-PARTS ----------------->
    // Context
    val cxt = LocalContext.current
    // View model reference
    //val viewModel: HomeVm = hiltViewModel()
    val viewModel = viewModel<HomeVm>(viewModelStoreOwner = viewModelStore)
    // View state reference from view model
    val state = viewModel.viewState
    // <!----------- MAIN-COMPOSE-CONTROL-PARTS ------------------->

    LaunchedEffect(key1 = state.launchedEffectState) {
        // Initiate retrieval of books
        viewModel.onEvent(HomeUiEvent.GetMyBooks)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.DarkGray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            content = {
                item {

                    state.books.forEachIndexed { index, book ->
                        Row(
                            modifier = Modifier.fillMaxWidth()
                                .wrapContentHeight()
                                .padding(vertical = 25.dp),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                book.name,
                                style = MaterialTheme.typography.titleMedium
                            )
                        }

                    }
                }
            },
        )
    }
}