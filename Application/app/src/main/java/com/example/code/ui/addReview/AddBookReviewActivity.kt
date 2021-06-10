package com.example.code.ui.addReview

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.code.App
import com.example.code.R
import com.example.code.model.Review
import kotlinx.android.synthetic.main.activity_add_book_review.*
import kotlinx.coroutines.launch
import java.util.*

class AddBookReviewActivity : AppCompatActivity() {

  private val repository by lazy { App.repository }

  companion object {
    fun getIntent(context: Context) = Intent(context, AddBookReviewActivity::class.java)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_add_book_review)
    initUi()
  }

  private fun initUi() {
    lifecycleScope.launch {
      bookOption.adapter = ArrayAdapter(
          this@AddBookReviewActivity,
          android.R.layout.simple_spinner_dropdown_item,
          repository.getBooks().map { it.book.name }
      )
    }

    addReview.setOnClickListener { addBookReview() }
  }

  private fun addBookReview() = lifecycleScope.launch {
    val rating = reviewRating.rating.toInt()
    val bookId = repository.getBooks()
        .firstOrNull { it.book.name == bookOption.selectedItem }?.book?.id

    val imageUrl = bookImageUrl.text.toString()
    val notes = reviewNotes.text.toString()

    if (bookId != null && imageUrl.isNotBlank() && notes.isNotBlank()) {
      val bookReview = Review(
          bookId = bookId,
          rating = rating,
          notes = notes,
          imageUrl = imageUrl,
          lastUpdatedDate = Date(),
          entries = emptyList()
      )

      repository.addReview(bookReview)
      setResult(Activity.RESULT_OK)
      finish()
    }
  }
}