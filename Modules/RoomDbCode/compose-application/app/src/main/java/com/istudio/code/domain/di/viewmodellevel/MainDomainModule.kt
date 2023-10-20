package com.istudio.code.domain.di.viewmodellevel

import com.istudio.code.data.repository.AppRepositoryImpl
import com.istudio.code.domain.usecases.useCaseMain.AddBookUseCases
import com.istudio.code.domain.usecases.useCaseMain.ReviewBookUseCases
import com.istudio.code.domain.usecases.useCaseTypes.dbOperations.AddBookUseCase
import com.istudio.code.domain.usecases.useCaseTypes.dbOperations.AddGenreDataUseCase
import com.istudio.code.domain.usecases.useCaseTypes.dbOperations.DeleteBookUseCase
import com.istudio.code.domain.usecases.useCaseTypes.dbOperations.GetBooksAndGenreUseCase
import com.istudio.code.domain.usecases.useCaseTypes.dbOperations.GetBooksUseCase
import com.istudio.code.domain.usecases.useCaseTypes.dbOperations.RetrieveGenreDataUseCase
import com.istudio.code.domain.usecases.useCaseTypes.validationOperations.addBook.ValidateAllInputsUseCase
import com.istudio.code.domain.usecases.useCaseTypes.validationOperations.addBook.ValidateCategoryInputUseCase
import com.istudio.code.domain.usecases.useCaseTypes.validationOperations.addBook.ValidateDescriptionInputUseCase
import com.istudio.code.domain.usecases.useCaseTypes.validationOperations.addBook.ValidateTitleInputUseCase
import com.istudio.code.domain.usecases.useCaseTypes.validationOperations.reviewBook.ValidateBookSelectedUseCase
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
    fun provideAddBookUseCases(
        appRepositoryImpl: AppRepositoryImpl
    ): AddBookUseCases {
        return AddBookUseCases(
            validateAllInputs = ValidateAllInputsUseCase(),
            validateTitle = ValidateTitleInputUseCase(),
            validateDescription = ValidateDescriptionInputUseCase(),
            validateCategory = ValidateCategoryInputUseCase(),
            addGenreDataUseCase = AddGenreDataUseCase(appRepositoryImpl = appRepositoryImpl),
            retrieveGenreDataUseCase = RetrieveGenreDataUseCase(appRepositoryImpl = appRepositoryImpl),
            addBookUseCase = AddBookUseCase(appRepositoryImpl = appRepositoryImpl),
            getBooksAndGenreUseCase = GetBooksAndGenreUseCase(appRepositoryImpl = appRepositoryImpl),
            deleteBookUseCase = DeleteBookUseCase(appRepositoryImpl = appRepositoryImpl)
        )
    }


    @ViewModelScoped
    @Provides
    fun provideReviewBookUseCases(
        appRepositoryImpl: AppRepositoryImpl
    ) : ReviewBookUseCases {
        return ReviewBookUseCases(
            validateBookSelectedUseCase = ValidateBookSelectedUseCase(),
            getBooksUseCase = GetBooksUseCase(appRepositoryImpl)
        )
    }


}