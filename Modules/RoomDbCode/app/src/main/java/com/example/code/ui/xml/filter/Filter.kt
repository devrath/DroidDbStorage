package com.example.code.ui.xml.filter

sealed class Filter

class ByGenre(val genreId: String) : Filter()

class ByRating(val rating: Int) : Filter()