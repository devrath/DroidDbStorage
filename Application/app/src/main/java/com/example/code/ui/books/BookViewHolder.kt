package com.example.code.ui.books

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.code.model.Book
import com.example.code.model.relations.BookAndGenre
import kotlinx.android.synthetic.main.item_book.view.*

class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

  fun showData(bookAndGenre: BookAndGenre,
               onItemLongTap: (Book) -> Unit) = with(itemView) {
    val (book, genre) = bookAndGenre
    bookTitle.text = book.name
    bookGenre.text = genre.name
    bookDescription.text = book.description

    setOnLongClickListener {
      onItemLongTap(book)
      true
    }
  }
}