package com.istudio.code.presentation.modules.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.istudio.code.core.platform.coroutines.usecase.Result
import com.istudio.code.core.platform.coroutines.usecase.data
import com.istudio.code.core.platform.functional.UseCaseResult
import com.istudio.code.core.platform.uiEvent.UiText
import com.istudio.code.core.platform.viewmodel.BaseViewModel
import com.istudio.code.domain.database.models.Book
import com.istudio.code.domain.database.models.Review
import com.istudio.code.domain.usecases.useCaseMain.AddBookUseCases
import com.istudio.code.domain.usecases.useCaseMain.ReviewBookUseCases
import com.istudio.code.presentation.modules.addbook.states.AddBookResponseEvent
import com.istudio.code.presentation.modules.home.states.myBooks.MyBooksEvent
import com.istudio.code.presentation.modules.home.states.myBooks.MyBooksUiEvent
import com.istudio.code.presentation.modules.home.states.myBooks.MyBooksUiState
import com.istudio.code.presentation.modules.home.states.myReviews.MyReviewsEvent
import com.istudio.code.presentation.modules.home.states.myReviews.MyReviewsUIState
import com.istudio.code.presentation.modules.home.states.myReviews.MyReviewsUiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeVm @Inject constructor(
    private val addBookUseCases: AddBookUseCases,
    private val reviewBookUseCases: ReviewBookUseCases,
) : BaseViewModel<Unit>() {

    override fun setupPrerequisites(params: Unit) {}

    /** ****** Data states from Presentation layer attached to VM ****** **/
    /** DATA STATE: <--------> My Books <--------> **/
    var viewStateMyBooks by mutableStateOf(MyBooksUiState())
        private set
    /** DATA STATE: <--------> My Reviews <--------> **/
    var viewStateMyReviews by mutableStateOf(MyReviewsUIState())
        private set
    /** ****** Data states from Presentation layer attached to VM ****** **/



    /** ****** View model sets this state from inside VM, Composable observes this state ****** **/
    /** UI EVENT: <--------> My Books <--------> **/
    private val _uiEventMyBooks = Channel<MyBooksEvent>()
    val uiEventMyBooks = _uiEventMyBooks.receiveAsFlow()
    /** UI EVENT: <--------> My Reviews <--------> **/
    private val _uiEventMyReviews = Channel<MyReviewsEvent>()
    val uiEventMyReviews = _uiEventMyReviews.receiveAsFlow()
    /** ****** View model sets this state from inside VM, Composable observes this state ****** **/


    /** ****** VM observes the changes and Composable sets the changes form composable ****** **/
    /** ON EVENT: <--------> My Books <--------> **/
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
                    viewStateMyBooks = viewStateMyBooks.copy(book = event.book)
                }
            }
        }
    }
    /** ON EVENT: <--------> My Reviews <--------> **/
    fun onEvent(event: MyReviewsUiEvent){
        viewModelScope.launch {
            when (event) {
                is MyReviewsUiEvent.GetMyReviews -> {
                    // Retrieve reviews from Database
                    retrieveAllReviews()
                }
                is MyReviewsUiEvent.DeleteReview -> {
                    // Deleting the book from database
                    deleteReview(event.review)
                }
                is MyReviewsUiEvent.ConfirmDeleteReview -> {
                    viewStateMyReviews = viewStateMyReviews.copy(review = event.review)
                }
            }
        }
    }
    /** ****** VM observes the changes and Composable sets the changes form composable ****** **/


    /** <*********************> Use case invocations <*******************> **/

    /** <*******> Use case <My Books> <*******> **/
    /**
     * USE-CASE: Retrieving all books from database
     */
    private suspend fun retrieveAllBooks() {
        addBookUseCases.getBooksAndGenreUseCase.invoke()
            .onSuccess { flowOfBooks ->
                flowOfBooks.catch { error ->
                    useCaseError(UseCaseResult.Error(Exception(error)))
                }.collect{ books ->
                    viewStateMyBooks = viewStateMyBooks.copy(books = books)
                }
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
            addBookUseCases.deleteBookUseCase.invoke(book)
        }
    }
    /** <*******> Use case <My Books> <*******> **/

    /** <*******> Use case <Reviews> <********> **/
    /**
     * USE-CASE: Retrieving all reviews of all books
     */
    private fun retrieveAllReviews() {
        launchUseCase(catchException {
            Log.d("Error", it.message.toString())
        }){
            when (val result = reviewBookUseCases.getReviewsUseCase.invoke(HashMap())) {
                is Result.Success -> {
                    result.value.data?.catch {
                        useCaseError(UseCaseResult.Error(Exception(it)))
                    }?.collect{
                        viewStateMyReviews = viewStateMyReviews.copy(reviews = it)
                    }
                }
                is Result.Error -> {
                    useCaseError(UseCaseResult.Error(Exception(result.exception)))
                }
                is Result.Loading -> { }
            }

        }
    }
    /**
     * USE-CASE: Deleting review from database
     */
    private fun deleteReview(review: Review) {
      //  reviewBookUseCases
        viewModelScope.launch {
            reviewBookUseCases.deleteReviewUseCase
                .invoke(review).onSuccess {
                    //_uiEventMyReviews.send(MyReviewsEvent.RefreshData)
                }.onFailure {
                    viewModelScope.launch {
                        useCaseError(UseCaseResult.Error(Exception(it)))
                    }
                }
        }
    }
    /** <*******> Use case <Reviews> <********> **/

    /** <*********************> Use case invocations <*******************> **/




    /** ********************************* DISPLAY MESSAGES ****************************************/
    /**
     * ERROR HANDLING:
     * Displaying messages to the snack-bar
     */
    private suspend fun useCaseErrorMessage(result: UiText?) {
        result?.let { _uiEventMyBooks.send(MyBooksEvent.ShowSnackBar(it.toString())) }
    }

    /**
     * ERROR HANDLING:
     * For the Use cases
     */
    private suspend fun useCaseError(result: UseCaseResult.Error) {
        val uiEvent = UiText.DynamicString(result.exception.message.toString())
        _uiEventMyBooks.send(MyBooksEvent.ShowSnackBar(uiEvent.text))
    }
    /** ********************************* DISPLAY MESSAGES ****************************************/



}