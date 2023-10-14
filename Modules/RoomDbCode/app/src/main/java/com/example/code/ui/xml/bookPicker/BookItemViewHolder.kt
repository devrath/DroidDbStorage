package com.example.code.ui.xml.bookPicker

import android.annotation.SuppressLint
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView
import com.example.code.R
import com.example.code.databinding.ItemBookOptionBinding
import com.example.code.model.BookItem

class BookItemViewHolder(itemView: ItemBookOptionBinding) : RecyclerView.ViewHolder(itemView.root) {
  @SuppressLint("CutPasteId")
  fun showData(book: BookItem, onItemSelected: (String) -> Unit) = with(itemView) {
    itemView.findViewById<RadioButton>(R.id.bookOption)
    itemView.findViewById<RadioButton>(R.id.bookOption).isChecked = book.isSelected
    itemView.findViewById<RadioButton>(R.id.bookOption).text = book.name

    setOnClickListener { onItemSelected(book.bookId) }
  }
}