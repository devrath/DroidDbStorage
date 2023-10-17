package com.istudio.code.domain.di.applevel

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.istudio.code.domain.database.AppDatabase
import com.istudio.code.domain.database.AppDatabase.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext, AppDatabase::class.java, DATABASE_NAME
        ).allowMainThreadQueries().build()
    }

}