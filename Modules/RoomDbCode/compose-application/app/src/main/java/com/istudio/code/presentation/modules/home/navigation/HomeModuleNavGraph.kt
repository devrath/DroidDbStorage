package com.istudio.code.presentation.modules.home.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.istudio.code.presentation.modules.home.screens.bookReviews.ScreenBookReviews
import com.istudio.code.presentation.modules.home.screens.myBooks.MyBooksScreen
import com.istudio.code.presentation.modules.home.screens.ScreenReadingList

@Composable
fun HomeModuleNavGraph(
    navController: NavHostController,
    viewModelStore: ViewModelStoreOwner
) {
    NavHost(
        navController = navController,
        startDestination = HomeNavItem.MyBooks.route
    ) {
        composable(route = HomeNavItem.MyBooks.route) {
            MyBooksScreen(viewModelStore)
        }
        composable(route = HomeNavItem.BookReviews.route) {
            ScreenBookReviews(viewModelStore)
        }
        composable(route = HomeNavItem.ReadingList.route) {
            ScreenReadingList(viewModelStore)
        }
    }
}