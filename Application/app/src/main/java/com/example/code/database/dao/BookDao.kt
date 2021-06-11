package com.example.code.database.dao

import androidx.room.*
import com.example.code.model.Book
import com.example.code.model.relations.BookAndGenre

@Dao
interface BookDao {

  @Query("SELECT * FROM books")
  suspend fun getBooks(): List<BookAndGenre>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun addBook(book: Book)

  @Delete
  suspend fun removeBook(book: Book)

  @Query("SELECT * FROM books WHERE id = :bookId")
  suspend fun getBookById(bookId: String): Book
}