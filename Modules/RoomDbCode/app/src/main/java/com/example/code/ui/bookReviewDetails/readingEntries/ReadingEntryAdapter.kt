package com.example.code.ui.bookReviewDetails.readingEntries

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.code.databinding.ItemReadingEntryBinding
import com.example.code.model.ReadingEntry

class ReadingEntryAdapter(private val onItemLongTapped: (ReadingEntry) -> Unit)
  : RecyclerView.Adapter<ReadingEntryViewHolder>() {

  private val items = mutableListOf<ReadingEntry>()

  fun setData(newItems: List<ReadingEntry>) {
    items.clear()
    items.addAll(newItems)
    notifyDataSetChanged()
  }

  override fun getItemCount() = items.size

  override fun onBindViewHolder(holder: ReadingEntryViewHolder, position: Int) {
    holder.showData(items[position], onItemLongTapped)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReadingEntryViewHolder {
    val binding = ItemReadingEntryBinding
      .inflate(LayoutInflater.from(parent.context), parent, false)
    return ReadingEntryViewHolder(binding)
  }
}