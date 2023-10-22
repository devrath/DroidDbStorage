package com.istudio.code.presentation.modules.addReview

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.istudio.code.core.platform.functional.UseCaseResult
import com.istudio.code.core.platform.uiEvent.UiText
import com.istudio.code.domain.database.models.Review
import com.istudio.code.domain.usecases.useCaseMain.ReviewBookUseCases
import com.istudio.code.presentation.modules.addReview.states.AddReviewResponseEvent
import com.istudio.code.presentation.modules.addReview.states.AddReviewUiState
import com.istudio.code.presentation.modules.addReview.states.AddReviewViewEvent
import com.istudio.code.presentation.modules.addbook.states.AddBookResponseEvent
import com.istudio.code.presentation.modules.addbook.states.AddBookViewEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddReviewVm @Inject constructor(
    private val reviewBookUseCases : ReviewBookUseCases
) : ViewModel() {

    // Holds the data of all the widgets in the view
    var viewState by mutableStateOf(AddReviewUiState())
        private set

    // View model sets this state, Composable observes this state
    private val _uiEvent = Channel<AddReviewResponseEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    /** <************> UI Action is invoked from composable <************> **/
    fun onEvent(event: AddReviewViewEvent) {
        viewModelScope.launch {
            when (event){
                is AddReviewViewEvent.PerformAction -> {
                    initiateValidation()
                }
                is AddReviewViewEvent.GetBooksList -> {
                    getBooksList()
                }
                is AddReviewViewEvent.SetRatingsList -> {
                    viewState = viewState.copy(ratingsList = event.ratinglist)
                }
                is AddReviewViewEvent.SetRating -> {
                    viewState = viewState.copy(rating = event.rating)
                }
                is AddReviewViewEvent.SetReviewNotes -> {
                    viewState = viewState.copy(reviewNotes = event.reviewNotes)
                }
                is AddReviewViewEvent.SetBookTitle -> {
                    // Book Title
                    viewState = viewState.copy(bookTitle = event.title)
                }
                is AddReviewViewEvent.SetBookListExpandedState -> {
                    // Sate to indicate if the dropdown is expanded :-> Book Drop Down
                    viewState = viewState.copy(isBookListExpanded = event.isExpanded)
                }
                is AddReviewViewEvent.SetRatingsListExpandedState -> {
                    // Sate to indicate if the dropdown is expanded :-> Ratings Drop Down
                    viewState = viewState.copy(isRatingsListExpanded = event.isExpanded)
                }
                is AddReviewViewEvent.SetBookErrorState -> {
                    viewState = viewState.copy(isBookError = event.isError)
                }
                is AddReviewViewEvent.SetRatingErrorState -> {
                    viewState = viewState.copy(isRatingError = event.isError)
                }
                is AddReviewViewEvent.SetReviewErrorState -> {
                    viewState = viewState.copy(isReviewError = event.isError)
                }
                is AddReviewViewEvent.SetBook -> {
                    viewState = viewState.copy( book = event?.book)
                }
                else -> { }
            }
        }
    }
    /** <************> UI Action is invoked from composable <************> **/

    /** <*********************> Use case invocations <*******************> **/
    private fun getBooksList() {
        viewModelScope.launch {
            reviewBookUseCases.getBooksUseCase.invoke()
                .onSuccess {flowOfBooks ->
                    flowOfBooks.catch { error->
                        useCaseError(UseCaseResult.Error(Exception(error)))
                    }.collect{ books ->
                        viewState = viewState.copy(booksList = books)
                    }
                }.onFailure {
                    useCaseError(UseCaseResult.Error(Exception(it)))
                }
        }
    }

    private fun addReview() {
        viewModelScope.launch {

            val review = Review(
                rating = viewState.rating.toInt(),
                notes = viewState.reviewNotes,
                bookId = viewState.book?.id.toString(),
            )

            reviewBookUseCases.addReviewUseCase.invoke(review)
                .onSuccess {
                    _uiEvent.send(AddReviewResponseEvent.ReviewAddIsSuccessful)
                }.onFailure {
                    useCaseError(UseCaseResult.Error(Exception(it)))
                }
        }
    }

    private fun validateBookSelection()  {
        reviewBookUseCases.validateBookSelectedUseCase.invoke(viewState.bookTitle)
            .onSuccess {
                if(!it){
                    // failure
                    viewState = viewState.copy(isBookError = true)
                }else{
                    // success
                    viewState = viewState.copy(isBookError = false)
                }
            }.onFailure {
                viewModelScope.launch {
                    useCaseError(UseCaseResult.Error(Exception(it)))
                }
            }
    }

    private fun validateRatingSelection() {
        reviewBookUseCases.validateRatingSelectionUseCase.invoke(viewState.rating)
            .onSuccess {
                if(!it){
                    // failure
                    viewState = viewState.copy(isRatingError = true)
                }else{
                    // success
                    viewState = viewState.copy(isRatingError = false)
                }
            }.onFailure {
                viewModelScope.launch {
                    useCaseError(UseCaseResult.Error(Exception(it)))
                }
            }
    }

    private fun validateReviewNotesSelection() {
        reviewBookUseCases.validateReviewNotesUseCase.invoke(viewState.reviewNotes)
            .onSuccess {
                if(!it){
                    // failure
                    viewState = viewState.copy(isReviewError = true)
                }else{
                    // success
                    viewState = viewState.copy(isReviewError = false)
                }
            }.onFailure {
                viewModelScope.launch {
                    useCaseError(UseCaseResult.Error(Exception(it)))
                }
            }
    }
    /** <*********************> Use case invocations <*******************> **/

    /** <*********************> Initiate validations <*******************> **/
    private fun initiateValidation() {
            validateReviewNotesSelection()
            validateRatingSelection()
            validateBookSelection()
        if(!viewState.isReviewError && !viewState.isBookError && !viewState.isRatingError){
            viewModelScope.launch {
                addReview()
            }
        }
    }
    /** <*********************> Initiate validations <*******************> **/

    /** ********************************* DISPLAY MESSAGES ****************************************/
    /**
     * ERROR HANDLING:
     * Displaying messages to the snack-bar
     */
    private suspend fun useCaseErrorMessage(result: UiText?) {
        result?.let { _uiEvent.send(AddReviewResponseEvent.ShowSnackBar(it.toString())) }
    }

    /**
     * ERROR HANDLING:
     * For the Use cases
     */
    private suspend fun useCaseError(result: UseCaseResult.Error) {
        val uiEvent = UiText.DynamicString(result.exception.message.toString())
        _uiEvent.send(AddReviewResponseEvent.ShowSnackBar(uiEvent.text))
    }
    /** ********************************* DISPLAY MESSAGES ****************************************/



}