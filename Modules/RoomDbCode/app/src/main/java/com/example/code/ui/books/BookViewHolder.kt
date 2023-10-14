package com.example.code.ui.books

import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.code.R
import com.example.code.databinding.ItemBookBinding
import com.example.code.model.Book
import com.example.code.model.relations.BookAndGenre

class BookViewHolder(itemView: ItemBookBinding) : RecyclerView.ViewHolder(itemView.root) {

  fun showData(bookAndGenre: BookAndGenre,
               onItemLongTap: (Book) -> Unit) = with(itemView) {
    val (book, genre) = bookAndGenre
    itemView.findViewById<TextView>(R.id.bookTitle).text = book.name
    itemView.findViewById<TextView>(R.id.bookGenre).text = genre.name
    itemView.findViewById<TextView>(R.id.bookDescription).text = book.description
    setOnLongClickListener {
      onItemLongTap(book)
      true
    }
  }
}