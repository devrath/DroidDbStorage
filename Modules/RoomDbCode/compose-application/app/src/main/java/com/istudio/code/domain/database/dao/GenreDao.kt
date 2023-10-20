package com.istudio.code.domain.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.istudio.code.domain.database.models.Genre

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


}