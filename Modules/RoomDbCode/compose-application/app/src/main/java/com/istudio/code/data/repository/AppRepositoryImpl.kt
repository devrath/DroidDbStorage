package com.istudio.code.data.repository

import com.istudio.code.domain.database.dao.BookDao
import com.istudio.code.domain.database.dao.GenreDao
import com.istudio.code.domain.database.dao.ReviewDao
import com.istudio.code.domain.database.models.Book
import com.istudio.code.domain.database.models.Genre
import com.istudio.code.domain.database.models.Review
import com.istudio.code.domain.database.models.relations.BookAndGenre
import com.istudio.code.domain.database.models.relations.ReviewAndBook
import com.istudio.code.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val bookDao: BookDao,
    private val genreDao: GenreDao,
    private val reviewDao: ReviewDao
) : AppRepository {
    override fun addBook(book: Book) = bookDao.addBook(book)

    // We combined using 2 different DAO's to get the combined end result you need
    override fun getBooks(): Flow<List<BookAndGenre>> {
        return bookDao.getBooks()
    }

    override fun getAllReviews(): List<ReviewAndBook> {
        return reviewDao.getReviews()
    }

    override fun getGenres(): List<Genre> = genreDao.getGenres()

    override fun addGenres(genres: List<Genre>) = genreDao.addGenres(genres)

    override fun getGenreById(genreId: String): Genre = genreDao.getGenreById(genreId = genreId)

    override fun removeBook(book: Book) = bookDao.deleteBook(book)

    override fun addReview(review: Review) = reviewDao.addReview(review)

    override fun removeReview(review: Review) = reviewDao.deleteReview(review)

    override fun updateReview(review: Review) = reviewDao.updateReview(review)
    override fun getBooksByGenre(genreId: String): List<BookAndGenre> {
        return genreDao.getBooksByGenre(genreId).let { bookByGenre ->
            // If no books are available return a empty list
            val books = bookByGenre.books ?: return emptyList()
            // If books are present we return books that map to a genreID
            return books.map {
                BookAndGenre(it,bookByGenre.genre)
            }
        }
    }

}