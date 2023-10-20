package com.istudio.code.presentation.modules.addReview.content

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.hilt.navigation.compose.hiltViewModel
import com.istudio.code.presentation.modules.addReview.AddReviewVm

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddReviewContainer(
    modifier: Modifier = Modifier, onBackPress : () -> Unit
){

    // <!------------ MAIN-COMPOSE-CONTROL-PARTS ----------------->
    // Context
    val cxt = LocalContext.current
    // View model reference
    val viewModel: AddReviewVm = hiltViewModel()
    // View state reference from view model
    val state = viewModel.viewState
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

}