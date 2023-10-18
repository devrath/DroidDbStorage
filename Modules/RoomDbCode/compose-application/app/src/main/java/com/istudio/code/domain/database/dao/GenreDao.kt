package com.istudio.code.domain.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.istudio.code.domain.database.models.Genre

@Dao
interface GenreDao {

    @Query("SELECT * FROM genre")
    fun getGenres() : List<Genre>

    @Query("SELECT * FROM genre WHERE id=:genreId")
    fun getGenreById(genreId:String) : Genre

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addGenres(genre:List<Genre>)


}