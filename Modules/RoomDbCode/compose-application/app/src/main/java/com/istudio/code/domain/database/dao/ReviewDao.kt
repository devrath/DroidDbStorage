package com.istudio.code.domain.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.istudio.code.domain.database.models.Review

@Dao
interface ReviewDao {

    /**
     * OPERATION: Inserting
     *
     * Adding a new review to the Review table
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addReview(review : Review)

    /**
     * OPERATION: Deleting
     *
     * Updating the existing review in the Review table
     */
    @Delete
    fun deleteReview(review:Review)

    /**
     * OPERATION: Updating
     *
     * Updating the existing review in the Review table
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateReview(review:Review)

}