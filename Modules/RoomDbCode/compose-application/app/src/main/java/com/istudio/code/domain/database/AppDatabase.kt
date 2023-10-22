package com.istudio.code.domain.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.istudio.code.domain.database.AppDatabase.Companion.DATABASE_VERSION
import com.istudio.code.domain.database.converters.DateConverter
import com.istudio.code.domain.database.dao.BookDao
import com.istudio.code.domain.database.dao.GenreDao
import com.istudio.code.domain.database.dao.ReviewDao
import com.istudio.code.domain.database.models.Book
import com.istudio.code.domain.database.models.Genre
import com.istudio.code.domain.database.models.Review

@Database(
    entities = [
        Book::class, Genre::class, Review::class
    ],
    version = DATABASE_VERSION
)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val DATABASE_NAME = "LibraryDatabase"
        const val DATABASE_VERSION = 1

    }
    abstract fun bookDao(): BookDao
    abstract fun genreDao(): GenreDao
    abstract fun reviewDao(): ReviewDao
}