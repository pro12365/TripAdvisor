package com.example.tripassistant.ui.explore

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tripassistant.R
import com.example.tripassistant.RecyclerViewItemAdapter
import com.example.tripassistant.data.models.Places
import com.example.tripassistant.databinding.FragmentExploreBinding
import com.example.tripassistant.ui.models.Heading
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ExploreFragment : Fragment(R.layout.fragment_explore) {

    private lateinit var binding: FragmentExploreBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentExploreBinding.bind(view)

        binding.apply {

            restaurantChip.setOnClickListener {
                val action =
                    ExploreFragmentDirections.actionGlobalSearchFragment("Find Restaurants in...")
                findNavController().navigate(action)
            }

            hotelChip.setOnClickListener {
                val action =
                    ExploreFragmentDirections.actionGlobalSearchFragment("Find Hotels in...")
                findNavController().navigate(action)
            }

            val adapter = RecyclerViewItemAdapter()
            recyclerView.adapter = adapter
            adapter.submitList(listOf(Heading("Destinations travellers love")))
        }


    }

    private fun getTopCities(): List<Places> {
        return listOf(
        )
    }
}