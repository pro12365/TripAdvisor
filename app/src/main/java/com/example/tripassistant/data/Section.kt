package com.example.tripassistant.data

import com.example.tripassistant.data.RecyclerViewItems

data class Section(
    val heading: String,
    val childItems:List<RecyclerViewItems>
)
