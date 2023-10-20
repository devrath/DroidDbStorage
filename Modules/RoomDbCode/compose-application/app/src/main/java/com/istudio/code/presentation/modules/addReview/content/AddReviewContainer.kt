package com.istudio.code.presentation.modules.addReview.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.istudio.code.R
import com.istudio.code.core.platform.utils.composeUtils.ControlWrapper
import com.istudio.code.core.platform.utils.composeUtils.CustomEditText
import com.istudio.code.data.repository.AppRepositoryImpl
import com.istudio.code.domain.usecases.useCaseMain.ReviewBookUseCases
import com.istudio.code.domain.usecases.useCaseTypes.dbOperations.GetBooksUseCase
import com.istudio.code.domain.usecases.useCaseTypes.validationOperations.reviewBook.ValidateBookSelectedUseCase
import com.istudio.code.presentation.modules.addReview.AddReviewVm
import com.istudio.code.presentation.modules.addReview.states.AddReviewViewEvent
import com.istudio.code.presentation.modules.addbook.states.AddBookViewEvent
import kotlinx.coroutines.launch
import org.mockito.Mockito.mock
import org.mockito.kotlin.mock

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
fun CurrentScreen(state: AddReviewVm, onBackPress: () -> Unit) {

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
    // Rating values
    cxt.resources.getIntArray(R.array.rating_values)?.let { values ->
        state.onEvent(
            event = AddReviewViewEvent.SetRatingsList(values.map { strValue -> strValue })
        )
    }
    // Get books list from database
    state.onEvent(event = AddReviewViewEvent.GetBooksList)


    Scaffold(
        snackbarHost = { SnackbarHost(snackBarController) },
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
                var currentTitle = state.viewState.bookTitle
                var currentRating = state.viewState.rating
                // Get the expanded state from VM and use here : Books
                var curBooksDropDownExpState = state.viewState.isBookListExpanded
                var curRatingsDropDownExpState = state.viewState.isRatingsListExpanded

                // Books list
                val booksList = state.viewState.booksList
                // Ratings list
                val ratingsList = state.viewState.ratingsList


                ExposedDropdownMenuBox(
                    modifier = Modifier.fillMaxWidth(),
                    expanded = curBooksDropDownExpState,
                    onExpandedChange = {
                        // Update the expanded state in VM
                        state.onEvent(event = AddReviewViewEvent.SetBookListExpandedState(true))
                    }
                ) {
                    TextField(
                        value = currentTitle,
                        onValueChange = { },
                        readOnly = true,
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(
                                expanded = curBooksDropDownExpState
                            )
                        },
                        colors = ExposedDropdownMenuDefaults.textFieldColors(),
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth(),
                        placeholder = {
                            Text(text = cxt.getString(R.string.select_a_book))
                        }
                    )

                    ExposedDropdownMenu(
                        expanded = curBooksDropDownExpState,
                        onDismissRequest = {
                            // Update the expanded state in VM
                            state.onEvent(event = AddReviewViewEvent.SetBookListExpandedState(false))
                        }
                    ) {
                        booksList.forEachIndexed { index, item ->
                            DropdownMenuItem(
                                text = { Text(text = item.name) },
                                onClick = {
                                    // Update the expanded state in VM
                                    state.onEvent(
                                        event = AddReviewViewEvent.SetBookListExpandedState(false)
                                    )
                                    state.onEvent(event = AddReviewViewEvent.SetBookTitle(item.name))
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                ExposedDropdownMenuBox(
                    modifier = Modifier.fillMaxWidth(),
                    expanded = curRatingsDropDownExpState,
                    onExpandedChange = {
                        // Update the expanded state in VM
                        state.onEvent(event = AddReviewViewEvent.SetRatingsListExpandedState(true))
                    }
                ) {
                    TextField(
                        value = currentRating,
                        onValueChange = { },
                        readOnly = true,
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(
                                expanded = curRatingsDropDownExpState
                            )
                        },
                        colors = ExposedDropdownMenuDefaults.textFieldColors(),
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth(),
                        placeholder = {
                            Text(text = cxt.getString(R.string.select_a_rating))
                        }
                    )

                    ExposedDropdownMenu(
                        expanded = curRatingsDropDownExpState,
                        onDismissRequest = {
                            // Update the expanded state in VM
                            state.onEvent(
                                event = AddReviewViewEvent.SetRatingsListExpandedState(
                                    false
                                )
                            )
                        }
                    ) {
                        ratingsList.forEachIndexed { index, item ->
                            DropdownMenuItem(
                                text = { Text(text = item.toString()) },
                                onClick = {
                                    // Update the expanded state in VM
                                    state.onEvent(
                                        event = AddReviewViewEvent.SetRatingsListExpandedState(
                                            false
                                        )
                                    )
                                    state.onEvent(event = AddReviewViewEvent.SetRating(item.toString()))
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                CustomEditText() { review ->

                }
            }
        }
    }
}


@Composable
@Preview
private fun ScreenPreview() {
    val usecases: ReviewBookUseCases = mock()
    CurrentScreen(AddReviewVm(usecases)) {

    }
}
