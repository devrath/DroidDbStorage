package com.example.code.ui.bookPicker

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.code.model.BookItem
import kotlinx.android.synthetic.main.item_book_option.view.*

class BookItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

  fun showData(book: BookItem, onItemSelected: (String) -> Unit) = with(itemView) {
    bookOption.isChecked = book.isSelected
    bookOption.text = book.name

    setOnClickListener { onItemSelected(book.bookId) }
  }
}