package com.example.tripassistant.ui.search

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.tripassistant.R
import com.example.tripassistant.databinding.FragmentSearchBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SearchFragment : Fragment() {

    private val TAG = "SearchFragment"
    private lateinit var binding: FragmentSearchBinding
    private val args: SearchFragmentArgs by navArgs()
    private val viewModel: SearchFragmentViewModel by viewModels()
    private lateinit var adapter: SuggestionsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = SuggestionsAdapter(findNavController())
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

        viewModel.isLoading.observe(viewLifecycleOwner, {
            binding.swipeRefresh.isRefreshing = it
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
