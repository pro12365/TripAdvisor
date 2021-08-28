package com.example.tripassistant.data

class Image {
    private val sizes: Sizes = Sizes()
    fun getUrl(): String {
        return sizes.medium.url
    }

    fun setUrl(url:String): Image {
        sizes.medium.url=url
        return this
    }
}

class Sizes {
    val medium: Medium = Medium()
}

class Medium {
    var url: String = ""
}