package com.istudio.code.presentation.modules.home.screens.bookReviews

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
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


        // <!------------ MAIN-COMPOSE-CONTROL-PARTS ----------------->
        // Context
        val cxt = LocalContext.current
        // View model reference
        //val viewModel: HomeVm = hiltViewModel()
        val viewModel = viewModel<HomeVm>(viewModelStoreOwner = viewModelStore)
        // View state reference from view model
        val state = viewModel.viewStateMyBooks

        // Dialog visibility state
        var dialogDisplayState by remember { mutableStateOf(false) }
        // <!----------- MAIN-COMPOSE-CONTROL-PARTS ------------------->




    }
}