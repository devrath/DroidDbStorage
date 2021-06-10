package com.example.code.ui.readingListDetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.code.R
import com.example.code.model.Book
import com.example.code.model.ReadingList
import com.raywenderlich.android.librarian.model.relations.ReadingListsWithBooks
import com.example.code.ui.bookPicker.BookPickerDialogFragment
import com.example.code.ui.books.BookAdapter
import com.example.code.utils.createAndShowDialog
import com.example.code.utils.gone
import com.example.code.utils.visible
import kotlinx.android.synthetic.main.activity_reading_list_details.*

class ReadingListDetailsActivity : AppCompatActivity() {

  private val adapter by lazy { BookAdapter(::onItemLongTapped) }
  private var readingList: ReadingListsWithBooks? = null

  companion object {
    private const val KEY_BOOK_REVIEW = "book_review"

    fun getIntent(context: Context, readingList: ReadingListsWithBooks): Intent {
      val intent = Intent(context, ReadingListDetailsActivity::class.java)

      intent.putExtra(KEY_BOOK_REVIEW, readingList)
      return intent
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_reading_list_details)
    initUi()
  }

  private fun initUi() {
    readingList = intent.getParcelableExtra(KEY_BOOK_REVIEW)

    val data = readingList

    if (data == null) {
      finish()
      return
    }
    addBookToList.setOnClickListener { showBookPickerDialog() }
    pullToRefresh.setOnRefreshListener { refreshList() }

    toolbar.title = data.name

    if (data.books.isEmpty()) {
      noBooksView.visible()
      booksRecyclerView.gone()
    } else {
      noBooksView.gone()
      booksRecyclerView.visible()
    }

    booksRecyclerView.layoutManager = LinearLayoutManager(this)
    booksRecyclerView.adapter = adapter
    adapter.setData(data.books)
  }

  private fun refreshList() {
    val data = readingList

    if (data == null) {
      pullToRefresh.isRefreshing = false
      return
    }

    val refreshedList = readingList // TODO load from DB
    readingList = refreshedList

    adapter.setData(refreshedList?.books ?: emptyList())
    pullToRefresh.isRefreshing = false
  }

  private fun showBookPickerDialog() {
    val dialog = BookPickerDialogFragment { bookId ->
      addBookToReadingList(bookId)
    }

    dialog.show(supportFragmentManager, null)
  }

  private fun addBookToReadingList(bookId: String) {
    val data = readingList

    if (data != null) {
      val bookIds = (data.books.map { it.book.id } + bookId).distinct()

      val newReadingList = ReadingList(
          data.id,
          data.name
//          bookIds
      )
      // TODO update reading list

      refreshList()
    }
  }

  private fun removeBookFromReadingList(bookId: String) {
    val data = readingList

    if (data != null) {
      val bookIds = data.books.map { it.book.id } - bookId

      val newReadingList = ReadingList(
          data.id,
          data.name
//          bookIds
      )

      // TODO update reading list

      refreshList()
    }
  }

  private fun onItemLongTapped(book: Book) {
    createAndShowDialog(this,
        getString(R.string.delete_title),
        getString(R.string.delete_message, book.name),
        onPositiveAction = {
          removeBookFromReadingList(book.id)
        }
    )
  }
}