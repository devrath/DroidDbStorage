package com.example.code.ui.readingListDetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.code.R
import com.example.code.databinding.ActivityReadingListDetailsBinding
import com.example.code.model.Book
import com.example.code.model.ReadingList
import com.example.code.model.relations.ReadingListsWithBooks
import com.example.code.ui.bookPicker.BookPickerDialogFragment
import com.example.code.ui.books.BookAdapter
import com.example.code.utils.createAndShowDialog
import com.example.code.utils.gone
import com.example.code.utils.visible

class ReadingListDetailsActivity : AppCompatActivity() {

  private lateinit var binding: ActivityReadingListDetailsBinding

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
    binding = ActivityReadingListDetailsBinding.inflate(layoutInflater)
    setContentView(binding.root)
    initUi()
  }

  private fun initUi() {
    readingList = intent.getParcelableExtra(KEY_BOOK_REVIEW)

    val data = readingList

    if (data == null) {
      finish()
      return
    }
    binding.addBookToList.setOnClickListener { showBookPickerDialog() }
    binding.pullToRefresh.setOnRefreshListener { refreshList() }

    binding.toolbar.title = data.name

    if (data.books.isEmpty()) {
      binding.noBooksView.visible()
      binding.booksRecyclerView.gone()
    } else {
      binding.noBooksView.gone()
      binding.booksRecyclerView.visible()
    }

    binding.booksRecyclerView.layoutManager = LinearLayoutManager(this)
    binding.booksRecyclerView.adapter = adapter
    adapter.setData(data.books)
  }

  private fun refreshList() {
    val data = readingList

    if (data == null) {
      binding.pullToRefresh.isRefreshing = false
      return
    }

    val refreshedList = readingList // TODO load from DB
    readingList = refreshedList

    adapter.setData(refreshedList?.books ?: emptyList())
    binding.pullToRefresh.isRefreshing = false
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