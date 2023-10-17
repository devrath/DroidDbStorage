package com.istudio.code.domain.di.applevel

import com.istudio.code.domain.database.AppDatabase
import com.istudio.code.domain.database.dao.BookDao
import com.istudio.code.domain.database.dao.GenreDao
import com.istudio.code.domain.database.dao.ReadingListDao
import com.istudio.code.domain.database.dao.ReviewDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideBookDao(appDatabase: AppDatabase): BookDao {
        return appDatabase.bookDao()
    }

    @Singleton
    @Provides
    fun provideGenreDao(appDatabase: AppDatabase): GenreDao {
        return appDatabase.genreDao()
    }

    @Singleton
    @Provides
    fun provideReadingListDao(appDatabase: AppDatabase): ReadingListDao {
        return appDatabase.readingListDao()
    }


    @Singleton
    @Provides
    fun provideReviewDao(appDatabase: AppDatabase): ReviewDao {
        return appDatabase.reviewDao()
    }

}