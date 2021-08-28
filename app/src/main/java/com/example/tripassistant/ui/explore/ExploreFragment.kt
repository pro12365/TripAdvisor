package com.example.tripassistant.ui.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tripassistant.databinding.FragmentExploreBinding
import com.example.tripassistant.data.Image
import com.example.tripassistant.data.Location
import com.example.tripassistant.data.Section


class ExploreFragment : Fragment() {

    private lateinit var binding: FragmentExploreBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExploreBinding.inflate(inflater, container, false)

        binding.restaurant.setOnClickListener {
            val action =
                ExploreFragmentDirections.actionGlobalSearchFragment("Find Restaurants in...")
            findNavController().navigate(action)
        }

        binding.hotel.setOnClickListener {
            val action = ExploreFragmentDirections.actionGlobalSearchFragment("Find Hotels in...")
            findNavController().navigate(action)
        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter =
            ExploreAdapter(listOf(Section("Destinations Travellers love", getTopCities())))
    }

    private fun getTopCities(): List<Location> {
        return listOf(
            Location(
                id = "New_York_City",
                name = "New York City",
                images = listOf(Image().setUrl("http://api-images-www.triposo.com/20210615/gAAAAABhIR61N1yvqHL48BE1j1u7zVMCnZFv2BzkV-7Dj7RX7v3FOUT6qjAA_OoM0L8uSHjqDmRLgUQQ4Ssko0y5aZkHV9n10lKa82-xIJR8PNdAgEN5Yc05niWi3_rvV0dbDO-mEFMBpMAEzyiX5HYLcsW3J4GmueNC0gHErhexRcYd4FTWZqQ0IKufP24WvGAI4TEb6LAtbuybEpSYB2WAfW24ZYiviSjOsHsP_cuJxgOOnB4qYwlGXuf13vdaE0fDoclh7ieL762cWvSPexOvLbeRCIYUhDtKz5mpRXkSQ9JAY359Y_o="))
            ),
            Location(id = "Rome", name = "Rome"),
            Location(id = "Paris", name = "Paris"),
            Location(id = "London", name = "London")
        )
    }
}