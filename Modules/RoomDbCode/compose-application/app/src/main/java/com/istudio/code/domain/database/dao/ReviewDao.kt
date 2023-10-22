package com.istudio.code.domain.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.istudio.code.domain.database.models.Book
import com.istudio.code.domain.database.models.Review
import com.istudio.code.domain.database.models.relations.ReviewAndBook

@Dao
interface ReviewDao {

    /**
     * OPERATION: Retrieving
     *
     * Getting just a list of reviews
     */
    @Query("SELECT * FROM review")
    fun getReviews() : List<ReviewAndBook>

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