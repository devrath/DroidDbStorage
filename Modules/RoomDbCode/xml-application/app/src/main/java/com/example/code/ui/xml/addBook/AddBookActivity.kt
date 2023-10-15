package com.example.code.ui.xml.addBook

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.code.App
import com.example.code.R
import com.example.code.databinding.ActivityAddBookBinding
import com.example.code.model.Book
import com.example.code.utils.toast
import kotlinx.coroutines.launch

class AddBookActivity : AppCompatActivity() {

  private lateinit var binding: ActivityAddBookBinding

  private val repository by lazy { App.repository }

  companion object {
    fun getIntent(context: Context): Intent = Intent(context, AddBookActivity::class.java)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityAddBookBinding.inflate(layoutInflater)
    setContentView(binding.root)
    initUi()
  }

  private fun initUi() {
    binding.addBook.setOnClickListener { createBook() }

    lifecycleScope.launch {
      binding.genrePicker.adapter = ArrayAdapter(
          this@AddBookActivity,
          android.R.layout.simple_spinner_dropdown_item,
          repository.getGenres().map { it.name }
      )
    }
  }

  private fun createBook() = lifecycleScope.launch {
    val title = binding.bookTitle.text.toString()
    val description = binding.bookDescription.text.toString()
    val genreId = repository.getGenres().firstOrNull { it.name == binding.genrePicker.selectedItem }?.id

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