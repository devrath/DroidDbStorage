package com.istudio.code.ui.modules.home.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.istudio.code.R
import com.istudio.code.ui.modules.home.navigation.HomeModuleNavGraph
import com.istudio.code.ui.modules.home.navigation.HomeNavItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContainer(modifier: Modifier = Modifier) {

    val cxt = LocalContext.current

    // Data items to have the Bottom tabs
    val navItems = remember {
        mutableStateListOf(
            HomeNavItem.MyBooks,
            HomeNavItem.BookReviews,
            HomeNavItem.ReadingList
        )
    }

    // Scroll behaviour
    val scrollBehaviour = TopAppBarDefaults.pinnedScrollBehavior()
    // The state "rememberSavable" helps to save the state in configuration changes, We can save in viewmodel also
    var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }
    // Keeping track of navigation
    val navController = rememberNavController();


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
                    if(selectedItemIndex == 0){
                        // Display the filter Icon only if it is First Tab - MyBooks
                        IconButton(onClick = {

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
                            Text(text = item.title)
                        },
                        icon = {
                            Icon(
                                imageVector = if (selectedItemIndex == index) {
                                    item.iconSelected
                                } else {
                                    item.iconUnSelected
                                },
                                contentDescription = item.title
                            )
                        },
                    )
                }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            HomeModuleNavGraph(navController)
        }
    }
}