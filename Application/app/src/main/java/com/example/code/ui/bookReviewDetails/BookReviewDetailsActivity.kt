package com.example.code.ui.bookReviewDetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.code.App
import com.example.code.R
import com.example.code.model.ReadingEntry
import com.example.code.model.relations.BookReview
import com.example.code.ui.bookReviewDetails.readingEntries.AddReadingEntryDialogFragment
import com.example.code.ui.bookReviewDetails.readingEntries.ReadingEntryAdapter
import com.example.code.utils.createAndShowDialog
import com.example.code.utils.formatDateToText
import com.example.code.utils.toast
import kotlinx.android.synthetic.main.activity_book_review_details.*
import kotlinx.coroutines.launch
import java.util.*

class BookReviewDetailsActivity : AppCompatActivity() {

  private var bookReview: BookReview? = null
  private val adapter by lazy { ReadingEntryAdapter(::onItemLongTapped) }
  private val repository by lazy { App.repository }

  companion object {
    private const val KEY_BOOK_REVIEW = "book_review"

    fun getIntent(context: Context, review: BookReview): Intent {
      val intent = Intent(context, BookReviewDetailsActivity::class.java)

      intent.putExtra(KEY_BOOK_REVIEW, review)
      return intent
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_book_review_details)
    initUi()
  }

  private fun initUi() {
    readingEntriesRecyclerView.layoutManager = LinearLayoutManager(this)
    readingEntriesRecyclerView.adapter = adapter
    addReadingEntry.setOnClickListener {
      val dialog = AddReadingEntryDialogFragment { readingEntry ->
        addNewEntry(readingEntry)
      }
      dialog.show(supportFragmentManager, null)
    }

    loadData()
  }

  private fun loadData() {
    val review: BookReview = intent?.getParcelableExtra(KEY_BOOK_REVIEW) ?: return
    val reviewId = review.review.id

    displayData(reviewId)
  }

  private fun displayData(reviewId: String) = lifecycleScope.launch {
    refreshData(reviewId)
    val data = bookReview ?: return@launch
    val genre = repository.getGenreById(data.book.genreId)

    Glide.with(this@BookReviewDetailsActivity).load(data.review.imageUrl).into(bookImage)
    reviewTitle.text = data.book.name
    reviewRating.rating = data.review.rating.toFloat()
    reviewDescription.text = data.review.notes
    lastUpdated.text = formatDateToText(data.review.lastUpdatedDate)
    bookGenre.text = genre.name

    adapter.setData(data.review.entries)
  }

  private suspend fun refreshData(id: String) {
    val storedReview = repository.getReviewById(id)

    bookReview = storedReview
  }

  private fun onItemLongTapped(readingEntry: ReadingEntry) {
    createAndShowDialog(
        this,
        getString(R.string.delete_title),
        getString(R.string.delete_entry_message),
        onPositiveAction = {
          removeReadingEntry(readingEntry)
        }
    )
  }

  private fun addNewEntry(readingEntry: ReadingEntry) {
    val data = bookReview?.review ?: return

    val updatedReview = data.copy(entries = data.entries + readingEntry,
        lastUpdatedDate = Date())

    lifecycleScope.launch {
      repository.updateReview(updatedReview)
      toast("Entry added!")

      displayData(data.id)
    }
  }

  private fun removeReadingEntry(readingEntry: ReadingEntry) {
    val data = bookReview ?: return
    val currentReview = data.review

    val newReview = currentReview.copy(
        entries = currentReview.entries - readingEntry,
        lastUpdatedDate = Date()
    )
    lifecycleScope.launch {
      repository.updateReview(newReview)
      loadData()
    }
  }
}