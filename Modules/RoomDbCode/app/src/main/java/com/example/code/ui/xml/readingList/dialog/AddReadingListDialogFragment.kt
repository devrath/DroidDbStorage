package com.example.code.ui.xml.readingList.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import com.example.code.App
import com.example.code.R
import com.example.code.databinding.DialogAddReadingListBinding
import com.example.code.model.ReadingList
import kotlinx.coroutines.launch

class AddReadingListDialogFragment(private val onListAdded: () -> Unit) : DialogFragment() {

  private var _binding: DialogAddReadingListBinding? = null
  private val binding get() = _binding!!

  private val repository by lazy { App.repository }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    _binding = DialogAddReadingListBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding.addReadingList.setOnClickListener { createReadingList() }
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

  private fun createReadingList() {
    val readingListName = binding.readingListNameInput.text.toString()

    if (readingListName.isNotBlank()) {
      val readingList = ReadingList(name = readingListName)

      lifecycleScope.launch {
        repository.addReadingList(readingList)
        onListAdded()
        dismissAllowingStateLoss()
      }
    }
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}