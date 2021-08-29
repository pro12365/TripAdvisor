package com.example.tripassistant.ui.models

//Denotes a section with horizontally scrollable child items
data class Section(
    val heading: String,
    val childItems: List<RecyclerViewItems>
)
