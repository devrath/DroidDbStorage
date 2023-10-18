package com.istudio.code.domain.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.istudio.code.domain.database.models.ReadingList

@Dao
interface ReadingListDao {

    /**
     * OPERATION: Inserting
     *
     * Adding a new entry to the reading list
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertReadingList(readingList : ReadingList)

}