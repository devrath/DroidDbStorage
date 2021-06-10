package com.example.code.ui.reviews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.code.R
import com.raywenderlich.android.librarian.model.relations.BookReview

class BookReviewAdapter(
    private val onItemSelected: (BookReview) -> Unit,
    private val onItemLongTapped: (BookReview) -> Unit
) : RecyclerView.Adapter<BookReviewViewHolder>() {

  private val items = mutableListOf<BookReview>()

  fun setData(newItems: List<BookReview>) {
    items.clear()
    items.addAll(newItems)
    notifyDataSetChanged()
  }

  override fun getItemCount() = items.size

  override fun onBindViewHolder(holder: BookReviewViewHolder, position: Int) {
    holder.showData(items[position], onItemSelected, onItemLongTapped)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookReviewViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_book_review, parent, false)

    return BookReviewViewHolder(view)
  }
}