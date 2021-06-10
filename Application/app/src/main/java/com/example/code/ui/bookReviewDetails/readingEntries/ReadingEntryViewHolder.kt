package com.example.code.ui.bookReviewDetails.readingEntries

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.code.model.ReadingEntry
import com.example.code.utils.formatDateToText
import kotlinx.android.synthetic.main.item_reading_entry.view.*

class ReadingEntryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

  fun showData(readingEntry: ReadingEntry,
               onItemLongTapped: (ReadingEntry) -> Unit) = with(itemView) {
    entryNotes.text = readingEntry.comment
    dateOfEntry.text = formatDateToText(readingEntry.dateOfEntry)

    setOnLongClickListener {
      onItemLongTapped(readingEntry)

      true
    }
  }
}