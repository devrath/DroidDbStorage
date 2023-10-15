package com.example.code.ui.xml.bookPicker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.code.R
import com.example.code.databinding.DialogAddBookBinding
import com.example.code.model.BookItem

class BookPickerDialogFragment(private val onItemSelected: (String) -> Unit) : DialogFragment() {

  private var _binding: DialogAddBookBinding? = null
  private val binding get() = _binding!!


  private val adapter by lazy { BookItemAdapter() }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?): View? {
    _binding = DialogAddBookBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setStyle(STYLE_NO_TITLE, R.style.FragmentDialogTheme)

    initUi()
  }

  private fun initUi() {
    val books = listOf<BookItem>()

    binding.bookOptionsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    binding.bookOptionsRecyclerView.adapter = adapter

    adapter.setData(books)

    binding.addBook.setOnClickListener {
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

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}