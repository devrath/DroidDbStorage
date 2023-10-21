package com.istudio.code.data.repository

import com.istudio.code.domain.database.dao.BookDao
import com.istudio.code.domain.database.dao.GenreDao
import com.istudio.code.domain.database.dao.ReadingListDao
import com.istudio.code.domain.database.dao.ReviewDao
import com.istudio.code.domain.database.models.Book
import com.istudio.code.domain.database.models.Genre
import com.istudio.code.domain.database.models.ReadingList
import com.istudio.code.domain.database.models.Review
import com.istudio.code.domain.database.models.relations.BookAndGenre
import com.istudio.code.domain.repository.AppRepository
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val bookDao: BookDao,
    private val genreDao: GenreDao,
    private val readingListDao: ReadingListDao,
    private val reviewDao: ReviewDao
) : AppRepository {
    override fun addBook(book: Book) = bookDao.addBook(book)

    // We combined using 2 different DAO's to get the combined end result you need
    override fun getBooks(): List<BookAndGenre> {
        return bookDao.getBooks().map {
            BookAndGenre(it, genreDao.getGenreById(it.genreId))
        }
    }

    override fun getGenres(): List<Genre> = genreDao.getGenres()

    override fun addGenres(genres: List<Genre>) = genreDao.addGenres(genres)

    override fun getGenreById(genreId: String): Genre = genreDao.getGenreById(genreId = genreId)

    override fun getAllBooks(): List<Book> = bookDao.getBooks()

    override fun removeBook(book: Book) = bookDao.deleteBook(book)

    override fun addReview(review: Review) = reviewDao.addReview(review)

    override fun removeReview(review: Review) = reviewDao.deleteReview(review)

    override fun updateReview(review: Review) = reviewDao.updateReview(review)

    override fun addReadingList(readingList: ReadingList) {
        return readingListDao.insertReadingList(readingList)
    }

}