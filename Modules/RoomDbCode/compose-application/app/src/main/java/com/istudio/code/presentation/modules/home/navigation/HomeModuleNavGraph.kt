package com.istudio.code.presentation.modules.home.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.istudio.code.presentation.modules.home.screens.ScreenBookReviews
import com.istudio.code.presentation.modules.home.screens.ScreenMyBooks
import com.istudio.code.presentation.modules.home.screens.ScreenReadingList

@Composable
fun HomeModuleNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = HomeNavItem.MyBooks.route
    ) {
        composable(route = HomeNavItem.MyBooks.route) {
            ScreenMyBooks()
        }
        composable(route = HomeNavItem.BookReviews.route) {
            ScreenBookReviews()
        }
        composable(route = HomeNavItem.ReadingList.route) {
            ScreenReadingList()
        }
    }
}