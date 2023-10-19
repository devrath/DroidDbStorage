package com.istudio.code.presentation.modules.home.screens.myBooks

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.istudio.code.core.platform.utils.composeUtils.rememberLifecycleEvent
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

    // <!------------ MAIN-COMPOSE-CONTROL-PARTS ----------------->
    // Context
    val cxt = LocalContext.current
    // View model reference
    //val viewModel: HomeVm = hiltViewModel()
    val viewModel = viewModel<HomeVm>(viewModelStoreOwner = viewModelStore)
    // View state reference from view model
    val state = viewModel.booksList
    // <!----------- MAIN-COMPOSE-CONTROL-PARTS ------------------->

    val lifecycleEvent = rememberLifecycleEvent()
    LaunchedEffect(lifecycleEvent) {
        // This is used to refresh the screen on returning from another screen
        if (lifecycleEvent == Lifecycle.Event.ON_RESUME) {
            // initiate data reloading
            // Initiate retrieval of books
            viewModel.onEvent(HomeUiEvent.GetMyBooks)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            content = {
                item {
                    state.forEachIndexed { index, item ->
                        MyBooksItem(item)
                    }
                }
            },
        )
    }
}