package com.example.code.ui.xml.bookPicker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.code.databinding.ItemBookOptionBinding
import com.example.code.model.BookItem

class BookItemAdapter : RecyclerView.Adapter<BookItemViewHolder>() {

  private val items = mutableListOf<BookItem>()

  fun setData(newItems: List<BookItem>) {
    items.clear()
    items.addAll(newItems)
    notifyDataSetChanged()
  }

  override fun getItemCount() = items.size

  override fun onBindViewHolder(holder: BookItemViewHolder, position: Int) {
    holder.showData(items[position], ::selectItem)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookItemViewHolder {
    val binding = ItemBookOptionBinding
      .inflate(LayoutInflater.from(parent.context), parent, false)
    return BookItemViewHolder(binding)

  }

  private fun selectItem(bookId: String) {
    val index = items.indexOfFirst { it.bookId == bookId }

    items.forEach { it.isSelected = false }
    items[index].isSelected = true
    notifyDataSetChanged()
  }

  fun getSelectedItem(): String = items.firstOrNull { it.isSelected }?.bookId ?: ""
}