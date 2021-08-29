package com.example.tripassistant.ui.explore

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tripassistant.R
import com.example.tripassistant.data.models.City
import com.example.tripassistant.ui.models.Section
import com.example.tripassistant.databinding.FragmentExploreBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ExploreFragment : Fragment(R.layout.fragment_explore) {

    private lateinit var binding: FragmentExploreBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentExploreBinding.bind(view)

        binding.restaurantChip.setOnClickListener {
            val action =
                ExploreFragmentDirections.actionGlobalSearchFragment("Find Restaurants in...")
            findNavController().navigate(action)
        }

        binding.hotelChip.setOnClickListener {
            val action = ExploreFragmentDirections.actionGlobalSearchFragment("Find Hotels in...")
            findNavController().navigate(action)
        }

        binding.recyclerView.adapter =
            ExploreAdapter(listOf(Section("Destinations Travellers love", getTopCities())))
    }

    private fun getTopCities(): List<City> {
        return listOf(
        )
    }
}