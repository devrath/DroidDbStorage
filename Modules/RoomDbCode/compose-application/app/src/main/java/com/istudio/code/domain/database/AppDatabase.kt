package com.istudio.code.domain.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.istudio.code.domain.database.AppDatabase.Companion.DATABASE_VERSION
import com.istudio.code.domain.database.dao.BookDao
import com.istudio.code.domain.database.dao.GenreDao
import com.istudio.code.domain.database.dao.ReadingListDao
import com.istudio.code.domain.database.dao.ReviewDao
import com.istudio.code.domain.database.models.Book
import com.istudio.code.domain.database.models.Genre
import com.istudio.code.domain.database.models.ReadingList
import com.istudio.code.domain.database.models.Review

@Database(
    entities = [
        Book::class, Genre::class
    ],
    version = DATABASE_VERSION
)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val DATABASE_NAME = "LibraryDatabase"
        const val DATABASE_VERSION = 1

    }
    abstract fun bookDao(): BookDao
    abstract fun genreDao(): GenreDao
    abstract fun readingListDao(): ReadingListDao
    abstract fun reviewDao(): ReviewDao
}