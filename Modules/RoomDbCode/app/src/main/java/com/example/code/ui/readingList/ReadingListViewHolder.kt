package com.example.code.ui.readingList

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.code.R
import com.example.code.databinding.ItemReadingListBinding
import com.example.code.model.relations.ReadingListsWithBooks

class ReadingListViewHolder(itemView: ItemReadingListBinding) : RecyclerView.ViewHolder(itemView.root) {

  fun showData(readingList: ReadingListsWithBooks,
               onItemSelected: (ReadingListsWithBooks) -> Unit,
               onItemLongTapped: (ReadingListsWithBooks) -> Unit) = with(itemView) {

      itemView.findViewById<TextView>(R.id.readingListTitle).text  = readingList.name
      itemView.findViewById<TextView>(R.id.readingListNumberOfBooks).text = context.getString(
          R.string.reading_list_number_of_books,
          readingList.books.size)

      setOnClickListener { onItemSelected(readingList) }
    setOnLongClickListener {
      onItemLongTapped(readingList)
      true
    }
  }
}