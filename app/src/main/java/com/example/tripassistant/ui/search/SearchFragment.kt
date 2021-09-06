package com.example.tripassistant.ui.search

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.tripassistant.R
import com.example.tripassistant.RecyclerViewItemAdapter
import com.example.tripassistant.api.LocationApiResponse
import com.example.tripassistant.databinding.FragmentSearchBinding
import com.example.tripassistant.ui.models.SearchResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {

    private lateinit var binding: FragmentSearchBinding
    private val args: SearchFragmentArgs by navArgs()
    private val viewModel: SearchFragmentViewModel by viewModels()
    private lateinit var adapter: RecyclerViewItemAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSearchBinding.bind(view)
        adapter = RecyclerViewItemAdapter()

        adapter.addViewHolderEventListener { viewHolder ->
            if (viewHolder is SearchResult.ViewHolder) {
                viewHolder.itemView.setOnClickListener {
                    val action =
                        SearchFragmentDirections.actionSearchFragmentToHotel2((adapter.currentList[viewHolder.adapterPosition] as SearchResult).places)
                    findNavController().navigate(action)
                }
            }
        }

        adapter.submitList(viewModel.getDefaultData())
        binding.recyclerView.adapter = adapter
        binding.searchPallet.hint = args.searchHint
        binding.swipeRefresh.setColorSchemeColors(
            ContextCompat.getColor(
                requireContext(),
                R.color.colorPrimary
            )
        )

        binding.swipeRefresh.setOnRefreshListener {
            GlobalScope.launch(Dispatchers.Default) {
                delay(2000)
                binding.swipeRefresh.isRefreshing = false
            }
        }
        binding.searchPallet.doAfterTextChanged {
            if (it.isNullOrEmpty())
                adapter.submitList(viewModel.getDefaultData()) {
                    binding.recyclerView.scrollTo(
                        0,
                        0
                    )
                }

        }
        binding.arrowBack.setOnClickListener {
            hideKeyboard()
            findNavController().navigateUp()
        }

        viewModel.networkRequestStatus.observe(viewLifecycleOwner, { status ->
            binding.swipeRefresh.isRefreshing = status == LocationApiResponse.STATUS_LOADING
            Log.e("NRS", " $status")
        })

        viewModel.locationList.observe(viewLifecycleOwner, {
            adapter.submitList(it) {
                binding.recyclerView.smoothScrollToPosition(0)
            }
        })

        binding.searchPallet.setOnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER && v is EditText) {
                hideKeyboard()
                viewModel.getSuggestion(v.text.toString())
            }
            false
        }
    }

    private fun hideKeyboard() {
        val imm =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.searchPallet.windowToken, 0)
    }

}
