package com.istudio.code.domain.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.istudio.code.domain.database.models.Genre
import com.istudio.code.domain.database.models.relations.BooksByGenre

@Dao
interface GenreDao {

    /**
     * OPERATION: Retrieving
     *
     * Getting just a list of Genre
     */
    @Query("SELECT * FROM genre")
    fun getGenres() : List<Genre>

    /**
     * OPERATION: Retrieving
     *
     * Getting a single genre based on a genreId
     */
    @Query("SELECT * FROM genre WHERE id=:genreId")
    fun getGenreById(genreId:String) : Genre

    /**
     * OPERATION: Adding
     *
     * Adding a list of genre
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addGenres(genre:List<Genre>)

    /**
     * OPERATION: Retrieving
     *
     * Getting list of books based on a perticular genre
     */
    @Transaction
    @Query("SELECT * FROM genre WHERE id= :genreId")
    fun getBooksByGenre(genreId : String) : BooksByGenre


    /**
     * OPERATION: Retrieving
     *
     * Getting list of Genre and all the books for each genre
     */
    /*@Transaction
    @Query("SELECT * FROM genre")
    fun getBooksByGenres(genreId : String) : List<BooksByGenre>*/
}