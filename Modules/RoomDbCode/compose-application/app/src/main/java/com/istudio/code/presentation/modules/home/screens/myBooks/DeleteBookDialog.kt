package com.istudio.code.presentation.modules.home.screens.myBooks

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAlert
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun DeleteBookDialog(
    dialogDisplayState: Boolean,
    bookDeleteConfirm: (Boolean) -> Unit
) {
    val contextForToast = LocalContext.current.applicationContext

    if (dialogDisplayState) {
        AlertDialog(
            onDismissRequest = {
                // Update the dialogDisplayState via Lambda when user clicks outside the dialog
                bookDeleteConfirm(false)
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        // Update the dialogDisplayState via Lambda on click of action button
                        bookDeleteConfirm(true)
                    }
                ) {
                    Text(text = "Yes")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        // Update the dialogDisplayState via Lambda on click of action button
                        bookDeleteConfirm(false)
                    }
                ) {
                    Text(text = "No")
                }
            },
            title = { Text(text = "Are you sure?") },
            text = { Text(text = "Are you sure you want to perform a action") },
            icon = {
                Icon(
                    imageVector = Icons.Default.AddAlert,
                    contentDescription = "Action to be performed"
                )
            }
        )
    }
}