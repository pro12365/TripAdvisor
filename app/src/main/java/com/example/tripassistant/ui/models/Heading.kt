package com.example.tripassistant.ui.models


//Denotes a heading to be displayed as a recyclerview item
data class Heading(val text: String) : RecyclerViewItems {
    override fun getItemViewType(): Int {
        return RecyclerViewItems.VIEWTYPE_HEADING
    }
}
