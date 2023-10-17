package com.istudio.code.domain.di

import com.istudio.code.domain.usecases.AddBookModuleUseCases
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
object AddBookDomainModule {

    @ViewModelScoped
    @Provides
    fun provideTrackerUseCases(): AddBookModuleUseCases {
        return AddBookModuleUseCases(
            validateAllInputs = ValidateAllInputsUseCase(),
            validateTitle = ValidateTitleInputUseCase(),
            validateDescription = ValidateDescriptionInputUseCase(),
            validateCategory = ValidateCategoryInputUseCase()
        )
    }

}