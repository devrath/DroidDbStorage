package com.example.code.ui.books

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.code.R
import com.example.code.model.Book
import com.raywenderlich.android.librarian.model.relations.BookAndGenre

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
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)

    return BookViewHolder(view)
  }
}