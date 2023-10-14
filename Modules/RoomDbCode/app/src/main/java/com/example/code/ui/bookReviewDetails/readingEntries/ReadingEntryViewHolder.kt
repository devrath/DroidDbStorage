package com.example.code.ui.bookReviewDetails.readingEntries

import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.code.R
import com.example.code.databinding.ItemReadingEntryBinding
import com.example.code.model.ReadingEntry
import com.example.code.utils.formatDateToText

class ReadingEntryViewHolder(itemView: ItemReadingEntryBinding) : RecyclerView.ViewHolder(itemView.root) {

  fun showData(readingEntry: ReadingEntry,
               onItemLongTapped: (ReadingEntry) -> Unit) = with(itemView) {
    itemView.findViewById<TextView>(R.id.entryNotes).text = readingEntry.comment
    itemView.findViewById<TextView>(R.id.dateOfEntry).text = formatDateToText(readingEntry.dateOfEntry)
    setOnLongClickListener {
      onItemLongTapped(readingEntry)
      true
    }
  }
}