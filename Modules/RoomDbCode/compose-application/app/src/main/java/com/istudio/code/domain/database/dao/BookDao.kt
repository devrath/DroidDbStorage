package com.istudio.code.domain.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.istudio.code.domain.database.models.Book
import com.istudio.code.domain.database.models.Genre
import com.istudio.code.domain.database.models.relations.BookAndGenre
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

    /**
     * OPERATION: Retrieving
     *
     * Getting just a list of books
     */
    @Query("SELECT * FROM books")
    fun getBooks() : Flow<List<BookAndGenre>>


    /**
     * OPERATION: Retrieving
     *
     * Getting a single genre based on a genreId
     */
    @Query("SELECT * FROM books WHERE id=:bookId")
    fun getBookById(bookId:String) : Book

    /**
     * OPERATION: Inserting
     *
     * Inserting a new book in database
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBook(book:Book)

    /**
     * OPERATION: Deleting
     *
     * Deleting a existing book in database
     */
    @Delete
    fun deleteBook(book : Book)

}