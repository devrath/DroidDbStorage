package com.example.code.ui.bookReviewDetails.readingEntries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.example.code.R
import com.example.code.model.ReadingEntry
import kotlinx.android.synthetic.main.dialog_add_reading_entry.*
import java.util.*

class AddReadingEntryDialogFragment(private val onReadingEntryAdded: (ReadingEntry) -> Unit)
  : DialogFragment() {

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.dialog_add_reading_entry, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initUi()
  }

  private fun initUi() {
    addReadingEntry.setOnClickListener { addEntry() }
  }

  private fun addEntry() {
    val input = readingEntryInput.text.toString()

    if (input.isNotBlank()) {
      onReadingEntryAdded(
        ReadingEntry(
          comment = input,
          dateOfEntry = Date()
      )
      )

      dismissAllowingStateLoss()
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setStyle(STYLE_NO_TITLE, R.style.FragmentDialogTheme)
  }

  override fun onStart() {
    super.onStart()
    dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
        WindowManager.LayoutParams.WRAP_CONTENT)
  }
}