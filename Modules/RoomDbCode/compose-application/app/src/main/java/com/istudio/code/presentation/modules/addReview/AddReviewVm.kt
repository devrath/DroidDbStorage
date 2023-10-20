package com.istudio.code.presentation.modules.addReview

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.istudio.code.domain.usecases.useCaseMain.ReviewBookUseCases
import com.istudio.code.presentation.modules.addReview.states.AddReviewResponseEvent
import com.istudio.code.presentation.modules.addReview.states.AddReviewUiState
import com.istudio.code.presentation.modules.addReview.states.AddReviewViewEvent
import com.istudio.code.presentation.modules.addbook.states.AddBookViewEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddReviewVm @Inject constructor(
    val reviewBookUseCases : ReviewBookUseCases
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
                is AddReviewViewEvent.SetBooksList -> {

                }
                is AddReviewViewEvent.SetRating -> {

                }
                is AddReviewViewEvent.SetReviewNotes -> {

                }
                is AddReviewViewEvent.SetBookTitle -> {
                    // Book Title
                    viewState = viewState.copy(bookTitle = event.title)
                }
                is AddReviewViewEvent.SetBookListExpandedState -> {
                    // Sate to indicate if the dropdown is expanded
                    viewState = viewState.copy(isBookListExpanded = event.isExpanded)
                }
            }
        }
    }
    /** <************> UI Action is invoked from composable <************> **/

}