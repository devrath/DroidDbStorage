package com.istudio.code.domain.di.viewmodellevel

import com.istudio.code.data.repository.AppRepositoryImpl
import com.istudio.code.domain.usecases.useCaseMain.AddBookUseCases
import com.istudio.code.domain.usecases.useCaseTypes.dbOperations.AddBookUseCase
import com.istudio.code.domain.usecases.useCaseTypes.dbOperations.AddGenreDataUseCase
import com.istudio.code.domain.usecases.useCaseTypes.dbOperations.DeleteBookUseCase
import com.istudio.code.domain.usecases.useCaseTypes.dbOperations.GetBooksUseCase
import com.istudio.code.domain.usecases.useCaseTypes.dbOperations.RetrieveGenreDataUseCase
import com.istudio.code.domain.usecases.useCaseTypes.validationOperations.ValidateAllInputsUseCase
import com.istudio.code.domain.usecases.useCaseTypes.validationOperations.ValidateCategoryInputUseCase
import com.istudio.code.domain.usecases.useCaseTypes.validationOperations.ValidateDescriptionInputUseCase
import com.istudio.code.domain.usecases.useCaseTypes.validationOperations.ValidateTitleInputUseCase
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
    ): AddBookUseCases {
        return AddBookUseCases(
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