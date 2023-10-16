package com.istudio.code.ui.modules.addbook.content

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import com.istudio.code.ui.modules.addbook.AddBookVm
import com.istudio.code.ui.modules.addbook.states.AddBookResponseEvent
import com.istudio.code.ui.modules.addbook.states.AddBookViewEvent
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
    // Exposed drop down menu
    var isExpanded by remember { mutableStateOf(false) }
    //  Error State: Title
    var isTitleError by remember { mutableStateOf(false) }
    //  Error State: Description
    var isDescriptionError by remember { mutableStateOf(false) }
    //  Error State: Category
    var isCategoryError by remember { mutableStateOf(false) }
    // Launched effect state
    val launchedEffectState by remember { mutableStateOf(false) }


    LaunchedEffect(key1 = launchedEffectState) {
        // <***********> Event is observed from View-Model <************>
        viewModel.uiEvent.collect { event ->
            when (event) {
                is AddBookResponseEvent.AddBookSuccess -> {
                    // Close the screen
                    onBackPress()
                }
                is AddBookResponseEvent.DescriptionFieldError -> isDescriptionError = true
                is AddBookResponseEvent.TitleFieldError -> isTitleError = true
                is AddBookResponseEvent.CategoryFieldError -> isCategoryError = true
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
                        isTitleError = false
                        viewModel.onEvent(AddBookViewEvent.SetTitle(updatedText))
                    },
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Left),
                    label = { Text(text = cxt.getString(R.string.title_enter_book_title)) },
                    placeholder = { Text(text = cxt.getString(R.string.str_title)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    isError = isTitleError
                )

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.description,
                    onValueChange = { updatedText ->
                        isDescriptionError = false
                        viewModel.onEvent(AddBookViewEvent.SetDescription(updatedText))
                    },
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Left),
                    label = { Text(text = cxt.getString(R.string.title_enter_book_description)) },
                    placeholder = { Text(text = cxt.getString(R.string.str_description)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    isError = isDescriptionError
                )

                Spacer(modifier = Modifier.height(20.dp))

                ExposedDropdownMenuBox(
                    modifier = Modifier.fillMaxWidth(),
                    expanded = isExpanded,
                    onExpandedChange = {
                        isExpanded = it
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
                                expanded = isExpanded
                            )
                        },
                        colors = ExposedDropdownMenuDefaults.textFieldColors(),
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth(),
                        placeholder = {
                            Text(text = cxt.getString(R.string.select_a_category))
                        },
                        isError = isCategoryError
                    )

                    ExposedDropdownMenu(
                        expanded = isExpanded,
                        onDismissRequest = { isExpanded = false }
                    ) {

                        val bookCategories = cxt.resources.getStringArray(R.array.book_categories)

                        bookCategories.forEachIndexed { index, item ->
                            DropdownMenuItem(
                                text = { Text(text = item) },
                                onClick = {
                                    viewModel.onEvent(AddBookViewEvent.SetCategory(item))
                                    isExpanded = false
                                    isCategoryError = false
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