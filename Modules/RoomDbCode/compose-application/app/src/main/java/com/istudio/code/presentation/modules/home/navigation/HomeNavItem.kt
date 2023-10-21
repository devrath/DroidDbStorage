package com.istudio.code.presentation.modules.home.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.MoreTime
import androidx.compose.material.icons.filled.Reviews
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.MoreTime
import androidx.compose.material.icons.outlined.Reviews
import androidx.compose.ui.graphics.vector.ImageVector
import com.istudio.code.R

sealed class HomeNavItem(
    val route: String,
    val iconSelected: ImageVector,
    val iconUnSelected: ImageVector,
    @StringRes val title: Int
) {
    // <--------- MyBooks Screen --------->
    object MyBooks : HomeNavItem(
        route = "myBooks",
        title = R.string.str_my_books,
        iconSelected = Icons.Filled.Book,
        iconUnSelected = Icons.Outlined.Book,
    )

    // <--------- BookReviews Screen --------->
    object BookReviews : HomeNavItem(
        route = "bookReviews",
        title = R.string.str_book_reviews,
        iconSelected = Icons.Filled.Reviews,
        iconUnSelected = Icons.Outlined.Reviews,
    )
}
