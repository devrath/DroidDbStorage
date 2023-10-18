package com.istudio.code.domain.repository

import com.istudio.code.domain.database.models.Book
import com.istudio.code.domain.database.models.Genre
import com.istudio.code.domain.database.models.ReadingList
import com.istudio.code.domain.database.models.Review
import com.istudio.code.domain.database.models.relations.BookAndGenre
import com.istudio.code.domain.database.models.relations.BookReview
import com.istudio.code.domain.database.models.relations.ReadingListsWithBooks
import kotlinx.coroutines.flow.Flow

interface AppRepository {
    fun addBook(book: Book)
    fun getBooks(): List<BookAndGenre>
    fun getAllBooks(): List<Book>
    fun getGenres(): List<Genre>
    fun addGenres(genres: List<Genre>)
    fun getGenreById(genreId: String): Genre
    fun removeBook(book:Book)
    fun addReview(review:Review)
    fun updateReview(review:Review)
    fun addReadingList(readingList : ReadingList)

}