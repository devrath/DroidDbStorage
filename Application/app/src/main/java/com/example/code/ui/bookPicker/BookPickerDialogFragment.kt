package com.example.code.ui.bookPicker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.code.R
import com.example.code.model.BookItem
import kotlinx.android.synthetic.main.dialog_add_book.*

class BookPickerDialogFragment(private val onItemSelected: (String) -> Unit) : DialogFragment() {

  private val adapter by lazy { BookItemAdapter() }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.dialog_add_book, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setStyle(STYLE_NO_TITLE, R.style.FragmentDialogTheme)

    initUi()
  }

  private fun initUi() {
    val books = listOf<BookItem>()

    bookOptionsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    bookOptionsRecyclerView.adapter = adapter

    adapter.setData(books)

    addBook.setOnClickListener {
      addBookToReadingList()
    }
  }

  private fun addBookToReadingList() {
    val selectedItem = adapter.getSelectedItem()
    if (selectedItem.isNotBlank()) {
      onItemSelected(selectedItem)
      dismissAllowingStateLoss()
    }
  }

  override fun onStart() {
    super.onStart()
    dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
        WindowManager.LayoutParams.WRAP_CONTENT)
  }
}