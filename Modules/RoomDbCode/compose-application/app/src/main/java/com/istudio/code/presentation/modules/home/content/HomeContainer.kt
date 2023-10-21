package com.istudio.code.presentation.modules.home.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.compose.rememberNavController
import com.istudio.code.R
import com.istudio.code.presentation.modules.addbook.AddBookActivity
import com.istudio.code.presentation.modules.home.navigation.HomeModuleNavGraph
import com.istudio.code.presentation.modules.home.navigation.HomeNavItem
import com.istudio.code.core.platform.extensions.startActivity
import com.istudio.code.presentation.modules.addReview.AddReviewActivity
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContainer(
    viewModelStore: ViewModelStoreOwner,
) {

    // Context
    val cxt = LocalContext.current
    // Coroutine to handle the animation
    val coroutineScope = rememberCoroutineScope()
    // Data items to have the Bottom tabs
    val navItems = remember {
        mutableStateListOf(HomeNavItem.MyBooks, HomeNavItem.BookReviews)
    }

    // Scroll behaviour
    val scrollBehaviour = TopAppBarDefaults.pinnedScrollBehavior()
    // The state "rememberSavable" helps to save the state in configuration changes, We can save in viewmodel also
    var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }
    // Keeping track of navigation
    val navController = rememberNavController();
    // For -> BottomSheetScaffold
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            BottomSheetContent()
        },
        sheetPeekHeight = 0.dp
    ) {

        // Scaffold: it contains bottom navigation bar and a NavHost(Contains all screens) for each screen
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehaviour.nestedScrollConnection),
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        var screenTitle = cxt.getString(R.string.str_home)
                        screenTitle = when (selectedItemIndex) {
                            0 -> cxt.getString(R.string.str_my_books)
                            1 -> cxt.getString(R.string.str_book_reviews)
                            else -> cxt.getString(R.string.str_reading_list)
                        }
                        Text(text = screenTitle)
                    },
                    actions = {
                        if (selectedItemIndex == 0) {
                            // Display the filter Icon only if it is First Tab - MyBooks
                            IconButton(onClick = {
                                coroutineScope.launch {
                                    bottomSheetScaffoldState.bottomSheetState.expand()
                                }
                            }) {
                                Icon(
                                    imageVector = Icons.Default.FilterList,
                                    contentDescription = cxt.getString(R.string.str_filter)
                                )
                            }
                        }
                    },
                    scrollBehavior = scrollBehaviour
                )
            },
            bottomBar = {
                NavigationBar {
                    navItems.forEachIndexed { index, item ->
                        NavigationBarItem(
                            // If both the index are same, return true indicating it is selected
                            selected = (selectedItemIndex == index),
                            onClick = {
                                selectedItemIndex = index
                                navController.navigate(item.route)
                            },
                            label = {
                                Text(text = cxt.getString(item.title))
                            },
                            icon = {
                                Icon(
                                    imageVector = if (selectedItemIndex == index) {
                                        item.iconSelected
                                    } else {
                                        item.iconUnSelected
                                    },
                                    contentDescription = cxt.getString(item.title)
                                )
                            },
                        )
                    }
                }
            },
            floatingActionButton = {
                FloatingButton(expanded = true, selectedItemIndex)
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                HomeModuleNavGraph(navController, viewModelStore)
            }
        }

    }

}

@Composable
fun BottomSheetContent() {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(4) { currentNumber ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(vertical = 25.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Current Row -> $currentNumber",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}

@Composable
fun FloatingButton(
    expanded: Boolean,
    selectedItemIndex: Int
) {
    val cxt = LocalContext.current
    ExtendedFloatingActionButton(
        onClick = {
            if (selectedItemIndex == 0) {
                // Start Add book activity
                cxt.startActivity<AddBookActivity>()
            } else if (selectedItemIndex == 1) {
                // Start Add review activity
                cxt.startActivity<AddReviewActivity>()
            } else if (selectedItemIndex == 2) {
                cxt.startActivity<AddBookActivity>()
            }
        },
        icon = {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = cxt.getString(R.string.str_add)
            )
        },
        text = { Text(text = cxt.getString(R.string.str_add)) },
        expanded = expanded
    )
}