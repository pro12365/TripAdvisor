package com.example.tripassistant.ui.hotels

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.tripassistant.api.ApiInterface
import com.example.tripassistant.api.RetrofitInstance
import com.example.tripassistant.databinding.FragmentHotelBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Hotel : Fragment() {

    private lateinit var binding: FragmentHotelBinding
    private val args: HotelArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHotelBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.title = "Hotels in ${args.location.name}"
        GlobalScope.launch {
            val service = RetrofitInstance.get().create(ApiInterface::class.java)
            val response = service.getHotelList(args.location.id)
            withContext(Dispatchers.Main) {
                binding.recyclerView.adapter = response.body()?.let { HotelAdapter(it.results) }
            }
        }
    }
}