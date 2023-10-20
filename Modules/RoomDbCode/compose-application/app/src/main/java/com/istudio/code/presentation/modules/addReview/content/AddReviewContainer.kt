package com.istudio.code.presentation.modules.addReview.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.istudio.code.R
import com.istudio.code.domain.usecases.useCaseMain.ReviewBookUseCases
import com.istudio.code.domain.usecases.useCaseTypes.validationOperations.reviewBook.ValidateBookSelectedUseCase
import com.istudio.code.presentation.modules.addReview.AddReviewVm
import com.istudio.code.presentation.modules.addReview.states.AddReviewViewEvent
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddReviewContainer(
    modifier: Modifier = Modifier, onBackPress: () -> Unit
) {
    // View model reference
    val viewModel: AddReviewVm = hiltViewModel()
    CurrentScreen(
        state = viewModel, onBackPress = onBackPress
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrentScreen(state: AddReviewVm,onBackPress :() -> Unit) {

    // <!------------ MAIN-COMPOSE-CONTROL-PARTS ----------------->
    // Context
    val cxt = LocalContext.current

    // View state reference from view model
    // <!------------ MAIN-COMPOSE-CONTROL-PARTS ----------------->

    // <!--------------------- CONTROLLERS ------------------------>
    // Keyboard controller
    val keyboardController = LocalSoftwareKeyboardController.current
    // SnackBar controller
    val snackBarController = remember { SnackbarHostState() }
    // Coroutine to handle the animation
    val coroutineScopeController = rememberCoroutineScope()
    // <!--------------------- CONTROLLERS ------------------------>

    // Scroll behaviour
    val scrollBehaviour = TopAppBarDefaults.pinnedScrollBehavior()


    Scaffold(
        snackbarHost = { SnackbarHost(snackBarController)  },
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehaviour.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = cxt.getString(R.string.str_add_review)) },
                navigationIcon = {
                    IconButton(onClick = {
                        // Close Screen
                        coroutineScopeController.launch { onBackPress.invoke() }
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = cxt.getString(R.string.str_navigate_back)
                        )
                    }
                },
                scrollBehavior = scrollBehaviour
            )
        }
    ) {

        Column(
            Modifier
                .fillMaxWidth()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {

                //var isExpanded by remember { mutableStateOf(false) }
                var bookTitle = state.viewState.bookTitle
                // Get the expanded state from VM and use here
                var curDropDownExpState = state.viewState.isBookListExpanded

                ExposedDropdownMenuBox(
                    modifier = Modifier.fillMaxWidth(),
                    expanded = curDropDownExpState,
                    onExpandedChange = {
                        // Update the expanded state in VM
                        state.onEvent(event = AddReviewViewEvent.SetBookListExpandedState(true))
                    },

                    ) {
                    TextField(
                        value = bookTitle,
                        onValueChange = { },
                        readOnly = true,
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(
                                expanded = curDropDownExpState
                            )
                        },
                        colors = ExposedDropdownMenuDefaults.textFieldColors(),
                        modifier = Modifier.menuAnchor().fillMaxWidth(),
                        placeholder = {
                            Text(text = cxt.getString(R.string.select_a_category))
                        }
                    )

                    ExposedDropdownMenu(
                        expanded = curDropDownExpState,
                        onDismissRequest = {
                            // Update the expanded state in VM
                            state.onEvent(event = AddReviewViewEvent.SetBookListExpandedState(false))
                        }
                    ) {

                        DropdownMenuItem(
                            text = {
                                Text(text = "Test")
                            },
                            onClick = {
                                // Update the expanded state in VM
                                state.onEvent(event = AddReviewViewEvent.SetBookListExpandedState(false))
                                state.onEvent(event = AddReviewViewEvent.SetBookTitle("Test"))
                            }
                        )

                    }
                }

            }

        }
    }
}


@Composable
@Preview
private fun ScreenPreview() {
    CurrentScreen(
        AddReviewVm(
            ReviewBookUseCases(
                validateBookSelectedUseCase = ValidateBookSelectedUseCase()
            )
        )
    ){

    }
}