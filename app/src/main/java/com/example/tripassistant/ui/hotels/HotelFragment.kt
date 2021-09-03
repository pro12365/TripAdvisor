package com.example.tripassistant.ui.hotels

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.tripassistant.R
import com.example.tripassistant.RecyclerViewItemAdapter
import com.example.tripassistant.api.LocationApi
import com.example.tripassistant.api.RetrofitInstance
import com.example.tripassistant.databinding.FragmentHotelBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class HotelFragment : Fragment(R.layout.fragment_hotel) {

    private lateinit var binding: FragmentHotelBinding
    private val args: HotelFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHotelBinding.bind(view)
        binding.toolbar.title = "Hotels in ${args.location.name}"
        val adapter = RecyclerViewItemAdapter()
        binding.recyclerView.adapter = adapter
        GlobalScope.launch {
            val service = RetrofitInstance.get().create(LocationApi::class.java)
            val response = service.getHotelList(args.location.id)
            withContext(Dispatchers.Main) {
                adapter.submitList(response.body()?.results)
            }
        }
    }
}