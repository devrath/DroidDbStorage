package com.istudio.code.domain.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.istudio.code.domain.database.models.Book

@Dao
interface BookDao {

    @Query("SELECT * FROM books")
    fun getBooks() : List<Book>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addBook(book:Book)



}