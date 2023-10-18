package com.istudio.code.domain.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.istudio.code.domain.database.models.Book

@Dao
interface BookDao {

    /**
     * OPERATION: Retrieving
     *
     * Getting just a list of books
     */
    @Query("SELECT * FROM books")
    fun getBooks() : List<Book>

    /**
     * OPERATION: Inserting
     *
     * Inserting a new book in database
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addBook(book:Book)

    /**
     * OPERATION: Deleting
     *
     * Deleting a existing book in database
     */
    @Delete
    fun deleteBook(book : Book)

}