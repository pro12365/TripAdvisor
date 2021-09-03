package com.example.tripassistant.ui.hotels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tripassistant.data.LocationRepository
import com.example.tripassistant.data.models.Hotels
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HotelFragmentViewModel @Inject constructor(
    val repository: LocationRepository
) : ViewModel() {

    val hotelList = MutableLiveData<List<Hotels>>()

}