package com.example.code.ui.xml.books

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.code.App
import com.example.code.R
import com.example.code.databinding.FragmentBooksBinding
import com.example.code.model.Book
import com.example.code.ui.xml.addBook.AddBookActivity
import com.example.code.ui.xml.filter.ByGenre
import com.example.code.ui.xml.filter.ByRating
import com.example.code.ui.xml.filter.Filter
import com.example.code.ui.xml.filter.FilterPickerDialogFragment
import com.example.code.utils.createAndShowDialog
import kotlinx.coroutines.launch

private const val REQUEST_CODE_ADD_BOOK = 101

class BooksFragment : Fragment() {


  private var _binding: FragmentBooksBinding? = null
  private val binding get() = _binding!!



  private val adapter by lazy { BookAdapter(::onItemLongTapped) }
  private var filter: Filter? = null
  private val repository by lazy { App.repository }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    _binding = FragmentBooksBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initUi()
  }

  override fun onStart() {
    super.onStart()
    loadBooks()
  }

  private fun initUi() {
    binding.pullToRefresh.setOnRefreshListener {
      loadBooks()
    }

    binding.booksRecyclerView.adapter = adapter
    binding.booksRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    binding.addBook.setOnClickListener {
      startActivityForResult(AddBookActivity.getIntent(requireContext()), REQUEST_CODE_ADD_BOOK)
    }

    binding.filterBooks.setOnClickListener {
      val dialog = FilterPickerDialogFragment { filter ->
        this.filter = filter

        loadBooks()
      }

      dialog.show(requireFragmentManager(), null)
    }
  }

  private fun loadBooks() = lifecycleScope.launch {
    binding.pullToRefresh.isRefreshing = true

    val books = when (val currentFilter = filter) {
      is ByGenre -> repository.getBooksByGenre(currentFilter.genreId)
      is ByRating -> repository.getBooksByRating(currentFilter.rating)
      else -> repository.getBooks()
    }

    adapter.setData(books)
    binding.pullToRefresh.isRefreshing = false
  }

  private fun onItemLongTapped(book: Book) {
    createAndShowDialog(requireContext(),
        getString(R.string.delete_title),
        getString(R.string.delete_message, book.name),
        onPositiveAction = { removeBook(book) }
    )
  }

  private fun removeBook(book: Book) {
    lifecycleScope.launch {
      repository.removeBook(book)
      loadBooks()
    }
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}