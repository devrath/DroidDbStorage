package com.example.code.ui.addBook

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.code.App
import com.example.code.R
import com.example.code.model.Book
import com.example.code.utils.toast
import kotlinx.android.synthetic.main.activity_add_book.*
import kotlinx.coroutines.launch

class AddBookActivity : AppCompatActivity() {

  private val repository by lazy { App.repository }

  companion object {
    fun getIntent(context: Context): Intent = Intent(context, AddBookActivity::class.java)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_add_book)
    initUi()
  }

  private fun initUi() {
    addBook.setOnClickListener { createBook() }

    lifecycleScope.launch {
      genrePicker.adapter = ArrayAdapter(
          this@AddBookActivity,
          android.R.layout.simple_spinner_dropdown_item,
          repository.getGenres().map { it.name }
      )
    }
  }

  private fun createBook() = lifecycleScope.launch {
    val title = bookTitle.text.toString()
    val description = bookDescription.text.toString()
    val genreId = repository.getGenres().firstOrNull { it.name == genrePicker.selectedItem }?.id

    if (title.isNotBlank() && description.isNotBlank() && !genreId.isNullOrBlank()) {
      val book = Book(
          name = title,
          description = description,
          genreId = genreId
      )

      repository.addBook(book)
      toast("Book added! :]")
      setResult(Activity.RESULT_OK)
      finish()
    }
  }
}