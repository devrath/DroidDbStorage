package com.istudio.code.data.repository

import com.istudio.code.domain.database.dao.BookDao
import com.istudio.code.domain.database.dao.GenreDao
import com.istudio.code.domain.database.dao.ReadingListDao
import com.istudio.code.domain.database.dao.ReviewDao
import com.istudio.code.repository.AppRepository
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val bookDao: BookDao,
    private val genreDao: GenreDao,
    private val readingListDao: ReadingListDao,
    private val reviewDao: ReviewDao
) : AppRepository {

}