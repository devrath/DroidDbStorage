package com.example.code.utils

import android.app.AlertDialog
import android.content.Context
import com.example.code.R

fun createAndShowDialog(context: Context,
    title: String,
    message: String,
    onPositiveAction: () -> Unit,
    onNegativeAction: () -> Unit = {}) {
  val dialog = AlertDialog.Builder(context)
      .setTitle(title)
      .setMessage(message)
      .setPositiveButton(R.string.yes) { dialog, _ ->
        onPositiveAction()
        dialog.dismiss()
      }
      .setNegativeButton(R.string.cancel) { dialog, _ ->
        onNegativeAction()
        dialog.dismiss()
      }
      .create()

  dialog.show()
}