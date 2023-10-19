package com.istudio.code.domain.di.viewmodellevel

import com.istudio.code.data.repository.AppRepositoryImpl
import com.istudio.code.domain.usecases.MainModuleUseCases
import com.istudio.code.domain.usecases.dbOperations.AddBookUseCase
import com.istudio.code.domain.usecases.dbOperations.AddGenreDataUseCase
import com.istudio.code.domain.usecases.dbOperations.DeleteBookUseCase
import com.istudio.code.domain.usecases.dbOperations.GetBooksUseCase
import com.istudio.code.domain.usecases.dbOperations.RetrieveGenreDataUseCase
import com.istudio.code.domain.usecases.validateAddBook.ValidateAllInputsUseCase
import com.istudio.code.domain.usecases.validateAddBook.ValidateCategoryInputUseCase
import com.istudio.code.domain.usecases.validateAddBook.ValidateDescriptionInputUseCase
import com.istudio.code.domain.usecases.validateAddBook.ValidateTitleInputUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object MainDomainModule {

    @ViewModelScoped
    @Provides
    fun provideTrackerUseCases(
        appRepositoryImpl: AppRepositoryImpl
    ): MainModuleUseCases {
        return MainModuleUseCases(
            validateAllInputs = ValidateAllInputsUseCase(),
            validateTitle = ValidateTitleInputUseCase(),
            validateDescription = ValidateDescriptionInputUseCase(),
            validateCategory = ValidateCategoryInputUseCase(),
            addGenreDataUseCase = AddGenreDataUseCase(appRepositoryImpl = appRepositoryImpl),
            retrieveGenreDataUseCase = RetrieveGenreDataUseCase(appRepositoryImpl = appRepositoryImpl),
            addBookUseCase = AddBookUseCase(appRepositoryImpl = appRepositoryImpl),
            getBooksUseCase = GetBooksUseCase(appRepositoryImpl = appRepositoryImpl),
            deleteBookUseCase = DeleteBookUseCase(appRepositoryImpl = appRepositoryImpl)
        )
    }

}