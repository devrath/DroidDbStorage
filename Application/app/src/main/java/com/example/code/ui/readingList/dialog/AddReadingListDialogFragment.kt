package com.example.code.ui.readingList.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import com.example.code.App
import com.example.code.R
import com.example.code.model.ReadingList
import kotlinx.android.synthetic.main.dialog_add_reading_list.*
import kotlinx.coroutines.launch

class AddReadingListDialogFragment(private val onListAdded: () -> Unit) : DialogFragment() {

  private val repository by lazy { App.repository }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.dialog_add_reading_list, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    addReadingList.setOnClickListener { createReadingList() }
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

  private fun createReadingList() {
    val readingListName = readingListNameInput.text.toString()

    if (readingListName.isNotBlank()) {
      val readingList = ReadingList(name = readingListName)

      lifecycleScope.launch {
        repository.addReadingList(readingList)
        onListAdded()
        dismissAllowingStateLoss()
      }
    }
  }
}