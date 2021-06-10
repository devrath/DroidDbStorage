package com.example.code.ui.reviews

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.code.R
import com.example.code.model.relations.BookReview
import kotlinx.android.synthetic.main.item_book_review.view.*

class BookReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

  fun showData(bookReview: BookReview,
               onItemSelected: (BookReview) -> Unit,
               onItemLongTapped: (BookReview) -> Unit) = with(itemView) {
    val (review, book) = bookReview

    reviewTitle.text = context.getString(R.string.review_title, book.name)
    reviewRating.rating = review.rating.toFloat()
    reviewNumberOfComments.text =
        context.getString(R.string.number_of_reading_entries, review.entries.size)
    reviewDescription.text = review.notes
    Glide.with(this).load(review.imageUrl).into(bookImage)

    setOnClickListener { onItemSelected(bookReview) }
    setOnLongClickListener {
      onItemLongTapped(bookReview)
      true
    }
  }
}