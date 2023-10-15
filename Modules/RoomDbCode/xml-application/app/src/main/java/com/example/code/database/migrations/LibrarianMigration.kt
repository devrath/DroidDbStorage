package com.example.code.database.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val migration_1_2 = object : Migration(1, 2) {
  override fun migrate(database: SupportSQLiteDatabase) {
    database.execSQL("ALTER TABLE Review ADD COLUMN lastUpdatedDate INTEGER NOT NULL DEFAULT 0")
  }
}

val migration_2_3 = object : Migration(2, 3) {
  override fun migrate(database: SupportSQLiteDatabase) {
    database.execSQL("ALTER TABLE Review ADD COLUMN entries TEXT NOT NULL DEFAULT ''")
  }
}