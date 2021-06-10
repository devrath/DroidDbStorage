package com.example.code.database.dao

import androidx.room.*
import com.example.code.model.Review
import com.raywenderlich.android.librarian.model.relations.BookReview
import kotlinx.coroutines.flow.Flow

@Dao
interface ReviewDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun addReview(review: Review)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun updateReview(review: Review)

  @Query("SELECT * FROM review")
  suspend fun getReviews(): List<BookReview>

  @Transaction
  @Query("SELECT * FROM review")
  fun getReviewsFlow(): Flow<List<BookReview>>

  @Query("SELECT * FROM review WHERE id = :reviewId")
  suspend fun getReviewById(reviewId: String): BookReview

  @Delete
  suspend fun removeReview(review: Review)

  @Query("SELECT * FROM review WHERE rating >= :rating")
  suspend fun getReviewsByRating(rating: Int): List<BookReview>
}