package com.example.code.repository

import com.example.code.model.Book
import com.example.code.model.Genre
import com.example.code.model.ReadingList
import com.example.code.model.Review
import com.example.code.model.relations.BookAndGenre
import com.example.code.model.relations.BookReview
import com.raywenderlich.android.librarian.model.relations.ReadingListsWithBooks
import kotlinx.coroutines.flow.Flow

interface LibrarianRepository {

  suspend fun addBook(book: Book)

  suspend fun getBooks(): List<BookAndGenre>

  suspend fun getBookById(bookId: String): Book

  suspend fun removeBook(book: Book)

  suspend fun getGenres(): List<Genre>

  suspend fun getGenreById(genreId: String): Genre

  suspend fun addGenres(genres: List<Genre>)

  suspend fun addReview(review: Review)

  suspend fun removeReview(review: Review)

  suspend fun getReviewById(reviewId: String): BookReview

  suspend fun getReviews(): List<BookReview>

  fun getReviewsFlow(): Flow<List<BookReview>>

  suspend fun updateReview(review: Review)

  suspend fun addReadingList(readingList: ReadingList)

  suspend fun getReadingLists(): List<ReadingListsWithBooks>

  fun getReadingListsFlow(): Flow<List<ReadingListsWithBooks>>

  suspend fun removeReadingList(readingList: ReadingList)

  suspend fun getBooksByGenre(genreId: String): List<BookAndGenre>

  suspend fun getBooksByRating(rating: Int): List<BookAndGenre>
}