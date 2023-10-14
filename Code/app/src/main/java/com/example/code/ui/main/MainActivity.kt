package com.example.code.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.code.R
import com.example.code.databinding.ActivityMainBinding
import com.example.code.ui.books.BooksFragment
import com.example.code.ui.readingList.ReadingListFragment
import com.example.code.ui.reviews.BookReviewsFragment

class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding

  private var reviewsFragment: BookReviewsFragment? = null
  private var readingListFragment: ReadingListFragment? = null
  private var booksFragment: BooksFragment? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    setTheme(R.style.AppTheme)

    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
    initUi()

    if (savedInstanceState == null) {
      displayNextFragment(R.id.books)
    }
  }

  private fun initUi() {
    binding.bottomNavigation.setOnNavigationItemSelectedListener { menuItem ->
      displayNextFragment(menuItem.itemId)
      true
    }
  }

  private fun displayNextFragment(itemId: Int) {
    when (itemId) {
      R.id.readingList -> {
        if (readingListFragment == null) {
          readingListFragment = ReadingListFragment()
        }

        displayFragment(readingListFragment!!)
      }

      R.id.bookReviews -> {
        if (reviewsFragment == null) {
          reviewsFragment = BookReviewsFragment()
        }

        displayFragment(reviewsFragment!!)
      }

      R.id.books -> {
        if (booksFragment == null) {
          booksFragment = BooksFragment()
        }

        displayFragment(booksFragment!!)
      }
    }
  }

  private fun displayFragment(fragment: Fragment) {
    supportFragmentManager.beginTransaction()
        .replace(R.id.fragmentContainer, fragment, null)
        .commit()
  }
}
