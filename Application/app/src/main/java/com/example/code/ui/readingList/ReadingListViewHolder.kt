package com.example.code.ui.readingList

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.code.R
import com.raywenderlich.android.librarian.model.relations.ReadingListsWithBooks
import kotlinx.android.synthetic.main.item_reading_list.view.*

class ReadingListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

  fun showData(readingList: ReadingListsWithBooks,
      onItemSelected: (ReadingListsWithBooks) -> Unit,
      onItemLongTapped: (ReadingListsWithBooks) -> Unit) = with(itemView) {
    readingListTitle.text = readingList.name
    readingListNumberOfBooks.text = context.getString(
        R.string.reading_list_number_of_books,
        readingList.books.size)

    setOnClickListener { onItemSelected(readingList) }
    setOnLongClickListener {
      onItemLongTapped(readingList)
      true
    }
  }
}