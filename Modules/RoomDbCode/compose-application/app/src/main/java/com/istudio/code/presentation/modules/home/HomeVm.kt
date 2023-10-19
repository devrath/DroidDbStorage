package com.istudio.code.presentation.modules.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.istudio.code.core.platform.functional.UseCaseResult
import com.istudio.code.core.platform.uiEvent.UiText
import com.istudio.code.domain.database.models.Book
import com.istudio.code.domain.usecases.MainModuleUseCases
import com.istudio.code.presentation.modules.home.states.HomeResponseEvent
import com.istudio.code.presentation.modules.home.states.HomeUiEvent
import com.istudio.code.presentation.modules.home.states.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeVm @Inject constructor(
    private val mainModuleUseCases: MainModuleUseCases,
) : ViewModel() {

    var viewState by mutableStateOf(HomeUiState())
        private set

    // View model sets this state, Composable observes this state
    private val _uiEvent = Channel<HomeResponseEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: HomeUiEvent) {
        viewModelScope.launch {
            when (event) {
                is HomeUiEvent.GetMyBooks -> {
                    // Retrieve books from Database
                    retrieveAllBooks()
                }
                is HomeUiEvent.DeleteBook -> {
                    deleteBook(event.book)
                }

                is HomeUiEvent.ConfirmDeleteBook -> {
                    viewState = viewState.copy(book = event.book)
                }            }
        }
    }

    private fun retrieveAllBooks() {
        mainModuleUseCases.getBooksUseCase.invoke()
            .onSuccess {
                viewState = viewState.copy(books = it)
            }.onFailure {
                viewModelScope.launch {
                    useCaseError(UseCaseResult.Error(Exception(it)))
                }
            }
    }

    private fun deleteBook(book: Book) {
        mainModuleUseCases.deleteBookUseCase
            .invoke(book).onSuccess {

            }.onFailure {
                viewModelScope.launch {
                    useCaseError(UseCaseResult.Error(Exception(it)))
                }
            }
    }





    /** ********************************* DISPLAY MESSAGES ****************************************/
    /**
     * ERROR HANDLING:
     * Displaying messages to the snack-bar
     */
    private suspend fun useCaseErrorMessage(result: UiText?) {
        result?.let { _uiEvent.send(HomeResponseEvent.ShowSnackBar(it.toString())) }
    }

    /**
     * ERROR HANDLING:
     * For the Use cases
     */
    private suspend fun useCaseError(result: UseCaseResult.Error) {
        val uiEvent = UiText.DynamicString(result.exception.message.toString())
        _uiEvent.send(HomeResponseEvent.ShowSnackBar(uiEvent.text))
    }
    /** ********************************* DISPLAY MESSAGES ****************************************/



}