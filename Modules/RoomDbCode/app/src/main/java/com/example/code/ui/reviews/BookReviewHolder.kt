package com.example.code.ui.reviews

import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.code.R
import com.example.code.databinding.ItemBookReviewBinding
import com.example.code.model.relations.BookReview

class BookReviewViewHolder(itemView: ItemBookReviewBinding) : RecyclerView.ViewHolder(itemView.root) {

  fun showData(bookReview: BookReview,
               onItemSelected: (BookReview) -> Unit,
               onItemLongTapped: (BookReview) -> Unit) = with(itemView) {
    val (review, book) = bookReview


    val imageView = itemView.findViewById<ImageView>(R.id.bookImage)
    Glide.with(this).load(review.imageUrl).into(imageView)


    itemView.findViewById<TextView>(R.id.reviewTitle).text  = context.getString(R.string.review_title, book.name)
    itemView.findViewById<TextView>(R.id.reviewNumberOfComments).text =
    context.getString(R.string.number_of_reading_entries, review.entries.size)
    itemView.findViewById<TextView>(R.id.reviewDescription).text  = review.notes

    itemView.findViewById<RatingBar>(R.id.reviewRating).rating = review.rating.toFloat()


    setOnClickListener { onItemSelected(bookReview) }
    setOnLongClickListener {
      onItemLongTapped(bookReview)
      true
    }
  }
}