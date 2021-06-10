package com.example.code.ui.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import com.example.code.App
import com.example.code.R
import kotlinx.android.synthetic.main.dialog_filter_books.*
import kotlinx.coroutines.launch

class FilterPickerDialogFragment(private val onFilterSelected: (Filter?) -> Unit)
  : DialogFragment() {

  private val repository by lazy { App.repository }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.dialog_filter_books, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initUi()
  }

  private fun initUi() {
    filterOptions.setOnCheckedChangeListener { _, checkedId ->
      updateOptions(checkedId)
    }

    lifecycleScope.launch {
      genrePicker.adapter = ArrayAdapter(
          requireContext(),
          android.R.layout.simple_spinner_dropdown_item,
          repository.getGenres().map { it.name }
      )
    }

    selectFilter.setOnClickListener { filterBooks() }
  }

  private fun filterBooks() = lifecycleScope.launch {
    val selectedGenre = repository.getGenres().firstOrNull { genre ->
      genre.name == genrePicker.selectedItem
    }?.id

    val rating = ratingPicker.rating.toInt()

    if (selectedGenre == null && filterOptions.checkedRadioButtonId == R.id.byGenreFilter) {
      return@launch
    }

    if ((rating < 1 || rating > 5) && filterOptions.checkedRadioButtonId == R.id.byRatingFilter) {
      return@launch
    }

    val filter = when (filterOptions.checkedRadioButtonId) {
      R.id.byGenreFilter -> ByGenre(selectedGenre ?: "")
      R.id.byRatingFilter -> ByRating(ratingPicker.rating.toInt())
      else -> null
    }

    onFilterSelected(filter)
    dismissAllowingStateLoss()
  }

  private fun updateOptions(checkedId: Int) {
    if (checkedId == noFilter.id) {
      genrePicker.visibility = View.GONE
      ratingPicker.visibility = View.GONE
    }

    genrePicker.visibility = if (checkedId == byGenreFilter.id) View.VISIBLE else View.GONE
    ratingPicker.visibility = if (checkedId == byRatingFilter.id) View.VISIBLE else View.GONE
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setStyle(STYLE_NO_TITLE, R.style.FragmentDialogTheme)
  }

  override fun onStart() {
    super.onStart()
    dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
        WindowManager.LayoutParams.WRAP_CONTENT)
  }
}