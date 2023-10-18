package com.istudio.code.presentation.modules.addbook.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.istudio.code.R
import com.istudio.code.presentation.modules.addbook.AddBookVm
import com.istudio.code.presentation.modules.addbook.states.AddBookResponseEvent
import com.istudio.code.presentation.modules.addbook.states.AddBookViewEvent
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddBookContainer(modifier: Modifier = Modifier, onBackPress: () -> Unit) {

    // <!------------ MAIN-COMPOSE-CONTROL-PARTS ----------------->
    // Context
    val cxt = LocalContext.current
    // View model reference
    val viewModel: AddBookVm = hiltViewModel()
    // View state reference from view model
    val state = viewModel.viewState
    // <!----------- MAIN-COMPOSE-CONTROL-PARTS ------------------->

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


    LaunchedEffect(key1 = state.launchedEffectState) {

        // Insert categories in the database
        val bookCategories = cxt.resources.getStringArray(R.array.book_categories)
        viewModel.insertGenreToDb(bookCategories)

        // Set the genre list in view model
        val genreList = viewModel.retrieveGenreToDb()
        viewModel.onEvent(AddBookViewEvent.SetGenreList(genreList))

        // <***********> Event is observed from View-Model <************>
        viewModel.uiEvent.collect { event ->
            when (event) {
                is AddBookResponseEvent.AddBookSuccess -> {
                    // Close the screen
                    onBackPress()
                }
                is AddBookResponseEvent.DescriptionFieldError -> {
                    viewModel.onEvent(AddBookViewEvent.SetIsDescriptionError(true))
                }
                is AddBookResponseEvent.TitleFieldError -> {
                    viewModel.onEvent(AddBookViewEvent.SetIsTitleError(true))
                }
                is AddBookResponseEvent.CategoryFieldError -> {
                    viewModel.onEvent(AddBookViewEvent.SetIsCategoryError(true))
                }
                else -> Unit
            }
        }
        // <***********> Event is observed from View-Model <************>
    }


    Scaffold(
        snackbarHost = { SnackbarHost(snackBarController) },
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehaviour.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = cxt.getString(R.string.str_add_book))
                },
                navigationIcon = {
                    IconButton(onClick = {
                        coroutineScopeController.launch {
                            // Close
                            onBackPress.invoke()
                        }
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
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.title,
                    onValueChange = { updatedText ->
                        viewModel.onEvent(AddBookViewEvent.SetIsTitleError(false))
                        viewModel.onEvent(AddBookViewEvent.SetTitle(updatedText))
                    },
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Left),
                    label = { Text(text = cxt.getString(R.string.title_enter_book_title)) },
                    placeholder = { Text(text = cxt.getString(R.string.str_title)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    isError = state.isTitleError
                )

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.description,
                    onValueChange = { updatedText ->
                        viewModel.onEvent(AddBookViewEvent.SetIsDescriptionError(false))
                        viewModel.onEvent(AddBookViewEvent.SetDescription(updatedText))
                    },
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Left),
                    label = { Text(text = cxt.getString(R.string.title_enter_book_description)) },
                    placeholder = { Text(text = cxt.getString(R.string.str_description)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    isError = state.isDescriptionError
                )

                Spacer(modifier = Modifier.height(20.dp))

                ExposedDropdownMenuBox(
                    modifier = Modifier.fillMaxWidth(),
                    expanded = state.isExpanded,
                    onExpandedChange = {
                        viewModel.onEvent(AddBookViewEvent.SetIsExpanded(it))
                    }
                ) {
                    TextField(
                        value = state.category,
                        onValueChange = { value ->
                            viewModel.onEvent(AddBookViewEvent.SetCategory(value))
                        },
                        readOnly = true,
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(
                                expanded = state.isExpanded
                            )
                        },
                        colors = ExposedDropdownMenuDefaults.textFieldColors(),
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth(),
                        placeholder = {
                            Text(text = cxt.getString(R.string.select_a_category))
                        },
                        isError = state.isCategoryError
                    )

                    ExposedDropdownMenu(
                        expanded = state.isExpanded,
                        onDismissRequest = {
                            viewModel.onEvent(AddBookViewEvent.SetIsExpanded(false))
                        }
                    ) {

                        val bookCategories = viewModel.viewState.genreList

                        bookCategories.forEachIndexed { index, item ->
                            DropdownMenuItem(
                                text = { Text(text = item) },
                                onClick = {
                                    viewModel.onEvent(AddBookViewEvent.SetCategory(item))
                                    viewModel.onEvent(AddBookViewEvent.SetIsExpanded(false))
                                    viewModel.onEvent(AddBookViewEvent.SetIsCategoryError(false))
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        coroutineScopeController.launch {
                            keyboardController?.hide()
                        }
                        viewModel.onEvent(AddBookViewEvent.AddBookViewClick)
                    }
                ) {
                    Text(text = cxt.getString(R.string.str_add))
                }

            }

        }

    }
}


@Composable
@Preview
private fun ScreenPreview() {
    AddBookContainer() {}
}