package com.example.code.database.dao

import androidx.room.*
import com.example.code.model.ReadingList
import kotlinx.coroutines.flow.Flow

@Dao
interface ReadingListDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun addReadingList(readingList: ReadingList)

  @Query("SELECT * FROM readinglist")
  suspend fun getReadingLists(): List<ReadingList>

  @Query("SELECT * FROM readinglist")
  fun getReadingListsFlow(): Flow<List<ReadingList>>

  @Delete
  suspend fun removeReadingList(readingList: ReadingList)
}