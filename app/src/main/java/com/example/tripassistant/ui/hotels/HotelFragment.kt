package com.example.tripassistant.ui.hotels

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.tripassistant.R
import com.example.tripassistant.RecyclerViewItemAdapter
import com.example.tripassistant.databinding.FragmentHotelBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HotelFragment : Fragment(R.layout.fragment_hotel) {

    private lateinit var binding: FragmentHotelBinding
    private val args: HotelFragmentArgs by navArgs()
    private val viewModel: HotelFragmentViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHotelBinding.bind(view)
        binding.toolbar.title = "Hotels in ${args.location.name}"
        val adapter = RecyclerViewItemAdapter()
        binding.recyclerView.adapter = adapter
        viewModel.getHotelList(args.location.id)
        viewModel.hotelList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

    }
}