package com.example.tripassistant.ui.models

//A class must implement this to be shown as recyclerview item
interface RecyclerViewItems {

    companion object {
        const val VIEWTYPE_HEADING=0
        const val VIEWTYPE_PLACE=1
    }

    fun getItemViewType(): Int
}