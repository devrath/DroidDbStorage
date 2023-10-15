package com.example.code.ui.xml.readingList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.code.databinding.ItemReadingListBinding
import com.example.code.model.relations.ReadingListsWithBooks

class ReadingListAdapter(
    private val onItemSelected: (ReadingListsWithBooks) -> Unit,
    private val onItemLongTapped: (ReadingListsWithBooks) -> Unit
) : RecyclerView.Adapter<ReadingListViewHolder>() {

  private val items = mutableListOf<ReadingListsWithBooks>()

  fun setData(newItems: List<ReadingListsWithBooks>) {
    items.clear()
    items.addAll(newItems)
    notifyDataSetChanged()
  }

  override fun getItemCount() = items.size

  override fun onBindViewHolder(holder: ReadingListViewHolder, position: Int) {
    holder.showData(items[position], onItemSelected, onItemLongTapped)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReadingListViewHolder {
    val binding = ItemReadingListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return ReadingListViewHolder(binding)
  }
}