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
import com.example.code.databinding.DialogFilterBooksBinding
import kotlinx.coroutines.launch

class FilterPickerDialogFragment(private val onFilterSelected: (Filter?) -> Unit)
  : DialogFragment() {

  private var _binding: DialogFilterBooksBinding? = null
  private val binding get() = _binding!!

  private val repository by lazy { App.repository }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    _binding = DialogFilterBooksBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initUi()
  }

  private fun initUi() {
    binding.filterOptions.setOnCheckedChangeListener { _, checkedId ->
      updateOptions(checkedId)
    }

    lifecycleScope.launch {
      binding.genrePicker.adapter = ArrayAdapter(
          requireContext(),
          android.R.layout.simple_spinner_dropdown_item,
          repository.getGenres().map { it.name }
      )
    }

     binding.selectFilter.setOnClickListener { filterBooks() }
  }

  private fun filterBooks() = lifecycleScope.launch {
    val selectedGenre = repository.getGenres().firstOrNull { genre ->
      genre.name == binding.genrePicker.selectedItem
    }?.id

    val rating = binding.ratingPicker.rating.toInt()

    if (selectedGenre == null && binding.filterOptions.checkedRadioButtonId == R.id.byGenreFilter) {
      return@launch
    }

    if ((rating < 1 || rating > 5) && binding.filterOptions.checkedRadioButtonId == R.id.byRatingFilter) {
      return@launch
    }

    val filter = when (binding.filterOptions.checkedRadioButtonId) {
      R.id.byGenreFilter -> ByGenre(selectedGenre ?: "")
      R.id.byRatingFilter -> ByRating(binding.ratingPicker.rating.toInt())
      else -> null
    }

    onFilterSelected(filter)
    dismissAllowingStateLoss()
  }

  private fun updateOptions(checkedId: Int) {
    if (checkedId == binding.noFilter.id) {
      binding.genrePicker.visibility = View.GONE
      binding.ratingPicker.visibility = View.GONE
    }

    binding.genrePicker.visibility = if (checkedId == binding.byGenreFilter.id) View.VISIBLE else View.GONE
    binding.ratingPicker.visibility = if (checkedId == binding.byRatingFilter.id) View.VISIBLE else View.GONE
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