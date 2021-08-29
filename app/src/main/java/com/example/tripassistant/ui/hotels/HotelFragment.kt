package com.example.tripassistant.ui.hotels

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.tripassistant.R
import com.example.tripassistant.api.LocationApi
import com.example.tripassistant.api.RetrofitInstance
import com.example.tripassistant.databinding.FragmentHotelBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HotelFragment : Fragment(R.layout.fragment_hotel) {

    private lateinit var binding: FragmentHotelBinding
    private val args: HotelFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHotelBinding.bind(view)
        binding.toolbar.title = "Hotels in ${args.location.name}"
        GlobalScope.launch {
            val service = RetrofitInstance.get().create(LocationApi::class.java)
            val response = service.getHotelList(args.location.id)
            withContext(Dispatchers.Main) {
                binding.recyclerView.adapter = response.body()?.let { HotelAdapter(it.results) }
            }
        }
    }
}