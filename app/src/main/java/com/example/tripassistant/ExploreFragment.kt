package com.example.tripassistant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.example.tripassistant.databinding.FragmentExploreBinding


class ExploreFragment : Fragment() {

    private lateinit var binding: FragmentExploreBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExploreBinding.inflate(inflater, container, false)

        binding.restaurant.setOnClickListener {
            val action=ExploreFragmentDirections.actionGlobalSearchFragment("Find Restaurants in...")
            findNavController().navigate(action)
        }

        binding.hotel.setOnClickListener {
            val action=ExploreFragmentDirections.actionGlobalSearchFragment("Find Hotels in...")
            findNavController().navigate(action)
        }


        return binding.root
    }
}