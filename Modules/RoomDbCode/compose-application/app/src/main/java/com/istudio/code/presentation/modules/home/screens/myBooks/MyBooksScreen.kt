package com.istudio.code.presentation.modules.home.screens.myBooks

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.istudio.code.presentation.modules.home.states.myBooks.MyBooksEvent
import com.istudio.code.presentation.modules.home.states.myBooks.MyBooksUiEvent

@Composable
fun MyBooksScreen(viewModelStore: ViewModelStoreOwner) {
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
    val state = viewModel.viewStateMyBooks

    // Dialog visibility state
    var dialogDisplayState by remember { mutableStateOf(false) }
    // <!----------- MAIN-COMPOSE-CONTROL-PARTS ------------------->

    // <!----------- OBSERVE THE ACTIONS from VM ------------------>
    LaunchedEffect(state.launchedEffectState) {
        viewModel.uiEventMyBooks.collect { event ->
            when (event) {
                is MyBooksEvent.ShowSnackBar -> {
                    Toast.makeText(cxt,event.message,Toast.LENGTH_LONG).show()
                }
            }
        }
    }
    // <!----------- OBSERVE THE ACTIONS from VM ------------------>

    // <!---- OBSERVE EVENT when you return from next screen  ----->
    val lifecycleEvent = rememberLifecycleEvent()
    LaunchedEffect(lifecycleEvent) {
        // This is used to refresh the screen on returning from another screen
        if (lifecycleEvent == Lifecycle.Event.ON_RESUME) {
            // initiate data reloading by retrieving the books from database again
            viewModel.onEvent(MyBooksUiEvent.GetMyBooks)
        }
    }
    // <!---- OBSERVE EVENT when you return from next screen  ----->

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
                    state.books.forEachIndexed { index, item ->
                        MyBooksItem(item){
                            dialogDisplayState = true
                            viewModel.onEvent(MyBooksUiEvent.ConfirmDeleteBook(item.book))
                        }
                    }
                }
            },
        )
    }

    if (dialogDisplayState) {
        DeleteBookDialog(dialogDisplayState) { newValue ->
            dialogDisplayState = false
            if(newValue){
                viewModel.viewStateMyBooks.book?.let {
                    viewModel.onEvent(MyBooksUiEvent.DeleteBook(it))
                }
            }
        }
    }
}