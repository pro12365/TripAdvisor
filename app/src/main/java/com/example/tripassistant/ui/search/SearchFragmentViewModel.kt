package com.example.tripassistant.ui.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tripassistant.api.ApiInterface
import com.example.tripassistant.api.RetrofitInstance
import com.example.tripassistant.data.Heading
import com.example.tripassistant.data.Location
import com.example.tripassistant.data.RecyclerViewItems
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

data class SearchFragmentViewModel(
    val locationList: MutableLiveData<List<Location>> = MutableLiveData<List<Location>>(),
    val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
) : ViewModel() {

    private val TAG = "SearchFragmentViewModel"

    fun getSuggestion(query: String) {

        val service = RetrofitInstance.get().create(ApiInterface::class.java)

        viewModelScope.launch(Dispatchers.IO) {
            isLoading.postValue(true)
            val response = service.getSuggestion("trigram:$query")
            if (response.isSuccessful)
                locationList.postValue(response.body()?.results)
            else
                response.errorBody()?.let { Log.e(TAG, it.string()) }
            isLoading.postValue(false)
        }
    }

    fun getDefaultData(): List<RecyclerViewItems> {
        return listOf(
            Heading("Popular Destinations"),
            Location(name = "Hyderabad", part_of = listOf("Telangana, India")),
            Location(name = "Visakhapatnam", part_of = listOf("Andhra Pradesh, India")),
            Location(name = "Srinagar", part_of = listOf("Jammu and Kashmir, India")),
            Location(name = "Bengaluru", part_of = listOf("Karnataka, India"))
        )
    }

}