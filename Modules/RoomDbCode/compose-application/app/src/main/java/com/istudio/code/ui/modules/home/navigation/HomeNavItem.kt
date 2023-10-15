package com.istudio.code.ui.modules.home.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.MoreTime
import androidx.compose.material.icons.filled.Reviews
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.MoreTime
import androidx.compose.material.icons.outlined.Reviews
import androidx.compose.ui.graphics.vector.ImageVector

sealed class HomeNavItem(
    val route: String,
    val iconSelected: ImageVector,
    val iconUnSelected: ImageVector,
    val title: String = ""
) {
    // <--------- MyBooks Screen --------->
    object MyBooks : HomeNavItem(
        route = "myBooks",
        title = "My Books",
        iconSelected = Icons.Filled.Book,
        iconUnSelected = Icons.Outlined.Book,
    )

    // <--------- BookReviews Screen --------->
    object BookReviews : HomeNavItem(
        route = "bookReviews",
        title = "Book Reviews",
        iconSelected = Icons.Filled.Reviews,
        iconUnSelected = Icons.Outlined.Reviews,
    )

    // <--------- ReadingList Screen --------->
    object ReadingList : HomeNavItem(
        route = "readingList",
        title = "Reading List",
        iconSelected = Icons.Filled.MoreTime,
        iconUnSelected = Icons.Outlined.MoreTime,
    )
}
