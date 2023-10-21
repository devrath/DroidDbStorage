package com.istudio.code.presentation.modules.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.istudio.code.core.platform.functional.UseCaseResult
import com.istudio.code.core.platform.uiEvent.UiText
import com.istudio.code.domain.database.models.Book
import com.istudio.code.domain.usecases.useCaseMain.AddBookUseCases
import com.istudio.code.presentation.modules.home.states.myBooks.MyBooksEvent
import com.istudio.code.presentation.modules.home.states.myBooks.MyBooksUiEvent
import com.istudio.code.presentation.modules.home.states.myBooks.MyBooksUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeVm @Inject constructor(
    private val addBookUseCases: AddBookUseCases,
) : ViewModel() {

    // Data states from Presentation layer attached to VM
    var viewState by mutableStateOf(MyBooksUiState())
        private set

    // View model sets this state from inside VM, Composable observes this state
    private val _uiEvent = Channel<MyBooksEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    // VM observes the changes and Composable sets the changes form composable
    fun onEvent(event: MyBooksUiEvent) {
        viewModelScope.launch {
            when (event) {
                is MyBooksUiEvent.GetMyBooks -> {
                    // Retrieve books from Database
                    retrieveAllBooks()
                }
                is MyBooksUiEvent.DeleteBook -> {
                    // Deleting the book from database
                    deleteBook(event.book)
                }

                is MyBooksUiEvent.ConfirmDeleteBook -> {
                    viewState = viewState.copy(book = event.book)
                }
            }
        }
    }

    /** <*********************> Use case invocations <*******************> **/

    /**
     * USE-CASE: Retrieving all books from database
     */
    private fun retrieveAllBooks() {
        addBookUseCases.getBooksAndGenreUseCase.invoke()
            .onSuccess {
                viewState = viewState.copy(books = it)
            }.onFailure {
                viewModelScope.launch {
                    useCaseError(UseCaseResult.Error(Exception(it)))
                }
            }
    }

    /**
     * USE-CASE: Deleting book from database
     */
    private fun deleteBook(book: Book) {
        viewModelScope.launch {
            addBookUseCases.deleteBookUseCase
                .invoke(book).onSuccess {
                    _uiEvent.send(MyBooksEvent.RefreshData)
                    //retrieveAllBooks()
                }.onFailure {
                    viewModelScope.launch {
                        useCaseError(UseCaseResult.Error(Exception(it)))
                    }
                }
        }
    }
    /** <*********************> Use case invocations <*******************> **/




    /** ********************************* DISPLAY MESSAGES ****************************************/
    /**
     * ERROR HANDLING:
     * Displaying messages to the snack-bar
     */
    private suspend fun useCaseErrorMessage(result: UiText?) {
        result?.let { _uiEvent.send(MyBooksEvent.ShowSnackBar(it.toString())) }
    }

    /**
     * ERROR HANDLING:
     * For the Use cases
     */
    private suspend fun useCaseError(result: UseCaseResult.Error) {
        val uiEvent = UiText.DynamicString(result.exception.message.toString())
        _uiEvent.send(MyBooksEvent.ShowSnackBar(uiEvent.text))
    }
    /** ********************************* DISPLAY MESSAGES ****************************************/



}