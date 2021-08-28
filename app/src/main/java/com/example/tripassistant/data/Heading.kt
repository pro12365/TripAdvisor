package com.example.tripassistant.data

import com.example.tripassistant.utils.ViewTypes

data class Heading(val text: String) : RecyclerViewItems {
    override fun getItemViewType(): Int {
        return ViewTypes.HEADER
    }
}
