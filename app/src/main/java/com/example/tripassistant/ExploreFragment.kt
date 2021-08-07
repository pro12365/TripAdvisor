package com.example.tripassistant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tripassistant.databinding.FragmentExploreBinding


class ExploreFragment : Fragment() {

    private lateinit var binding: FragmentExploreBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExploreBinding.inflate(inflater, container, false)
        return binding.root


    }

}