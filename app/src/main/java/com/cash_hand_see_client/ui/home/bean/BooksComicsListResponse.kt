package com.cash_hand_see_client.ui.home.bean


data class BooksComicsListResponse(var list: List<BooksComics> = listOf())

data class BooksComics(
    var number: Int,
    var pages: Int,
    var dpages: Int,
    var name: String,
    var url: String,
    var cover: String,
    var time: String,
    var latest: String
)
