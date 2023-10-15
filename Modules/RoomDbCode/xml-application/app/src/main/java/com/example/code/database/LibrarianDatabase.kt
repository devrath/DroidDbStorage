package com.example.code.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.code.database.converters.DateConverter
import com.example.code.database.converters.ReadingEntryConverter
import com.example.code.database.dao.BookDao
import com.example.code.database.dao.GenreDao
import com.example.code.database.dao.ReadingListDao
import com.example.code.database.dao.ReviewDao
import com.example.code.database.migrations.migration_1_2
import com.example.code.database.migrations.migration_2_3
import com.example.code.model.Book
import com.example.code.model.Genre
import com.example.code.model.ReadingList
import com.example.code.model.Review

const val DATABASE_VERSION = 3

@Database(
    entities = [Book::class, Genre::class, ReadingList::class, Review::class],
    version = DATABASE_VERSION
)
@TypeConverters(DateConverter::class, ReadingEntryConverter::class)
abstract class LibrarianDatabase : RoomDatabase() {

  companion object {
    private const val DATABASE_NAME = "Librarian"

    fun buildDatabase(context: Context): LibrarianDatabase {
      return Room.databaseBuilder(
          context,
          LibrarianDatabase::class.java,
          DATABASE_NAME
      )
          .addMigrations(migration_1_2, migration_2_3)
          .build()
    }
  }

  abstract fun bookDao(): BookDao

  abstract fun genreDao(): GenreDao

  abstract fun reviewDao(): ReviewDao

  abstract fun readingListDao(): ReadingListDao
}