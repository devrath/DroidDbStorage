package com.example.code.ui.books

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.code.databinding.ItemBookBinding
import com.example.code.model.Book
import com.example.code.model.relations.BookAndGenre

class BookAdapter(
    private val onItemLongTapped: (Book) -> Unit
) : RecyclerView.Adapter<BookViewHolder>() {

  private val items = mutableListOf<BookAndGenre>()

  fun setData(newItems: List<BookAndGenre>) {
    items.clear()
    items.addAll(newItems)
    notifyDataSetChanged()
  }

  override fun getItemCount() = items.size

  override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
    holder.showData(items[position], onItemLongTapped)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
    val binding = ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return BookViewHolder(binding)
  }
}