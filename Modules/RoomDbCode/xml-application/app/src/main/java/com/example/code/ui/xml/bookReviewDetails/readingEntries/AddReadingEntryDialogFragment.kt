package com.example.code.ui.xml.bookReviewDetails.readingEntries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.example.code.R
import com.example.code.databinding.DialogAddReadingEntryBinding
import com.example.code.model.ReadingEntry
import java.util.*

class AddReadingEntryDialogFragment(private val onReadingEntryAdded: (ReadingEntry) -> Unit)
  : DialogFragment() {

  private var _binding: DialogAddReadingEntryBinding? = null
  private val binding get() = _binding!!

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?): View? {
    _binding = DialogAddReadingEntryBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initUi()
  }

  private fun initUi() {
    binding.addReadingEntry.setOnClickListener { addEntry() }
  }

  private fun addEntry() {
    val input = binding.readingEntryInput.text.toString()

    if (input.isNotBlank()) {
      onReadingEntryAdded(
        ReadingEntry(
          comment = input,
          dateOfEntry = Date()
      )
      )

      dismissAllowingStateLoss()
    }
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

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}