package com.example.code.ui.xml.readingList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.code.App
import com.example.code.R
import com.example.code.databinding.FragmentReadingListBinding
import com.example.code.model.ReadingList
import com.example.code.model.relations.ReadingListsWithBooks
import com.example.code.ui.xml.readingList.dialog.AddReadingListDialogFragment
import com.example.code.ui.xml.readingListDetails.ReadingListDetailsActivity
import com.example.code.utils.createAndShowDialog
import com.example.code.utils.toast
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class ReadingListFragment : Fragment() {

  private var _binding: FragmentReadingListBinding? = null
  private val binding get() = _binding!!

  private val adapter by lazy { ReadingListAdapter(::onItemSelected, ::onItemLongTapped) }
  private val repository by lazy { App.repository }
  private val readingListsFlow by lazy { repository.getReadingListsFlow() }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    _binding = FragmentReadingListBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initListeners()
    initUi()
    loadReadingLists()
  }

  private fun initUi() {
    binding.readingListRecyclerView.layoutManager = LinearLayoutManager(context)
    binding.readingListRecyclerView.adapter = adapter
    binding.pullToRefresh.isEnabled = false
  }

  private fun loadReadingLists() = lifecycleScope.launch {
    readingListsFlow.catch { it.printStackTrace() }
        .collect { readingLists ->
          adapter.setData(readingLists)
        }
  }

  private fun initListeners() {
    binding.addReadingList.setOnClickListener {
      showAddReadingListDialog()
    }
  }

  private fun showAddReadingListDialog() {
    val fragmentManager = fragmentManager ?: return

    val dialog = AddReadingListDialogFragment {
      activity?.toast("List created!")
    }

    dialog.show(fragmentManager, null)
  }

  private fun onItemLongTapped(readingList: ReadingListsWithBooks) {
    createAndShowDialog(requireContext(),
        getString(R.string.delete_title),
        getString(R.string.delete_message, readingList.name),
        onPositiveAction = { removeReadingList(readingList) }
    )
  }

  private fun removeReadingList(readingList: ReadingListsWithBooks) {
    lifecycleScope.launch {
      repository.removeReadingList(ReadingList(readingList.id, readingList.name))
    }
  }

  private fun onItemSelected(readingList: ReadingListsWithBooks) {
    startActivity(ReadingListDetailsActivity.getIntent(requireContext(), readingList))
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}