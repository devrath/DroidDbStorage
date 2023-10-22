package com.istudio.code.presentation.modules.addbook

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
import com.istudio.code.domain.entities.input.AddBookAllInputs
import com.istudio.code.domain.entities.input.AddBookCategoryInput
import com.istudio.code.domain.entities.input.AddBookDescriptionInput
import com.istudio.code.domain.entities.input.AddBookTitleInput
import com.istudio.code.domain.usecases.useCaseMain.AddBookUseCases
import com.istudio.code.presentation.modules.addbook.states.AddBookResponseEvent
import com.istudio.code.presentation.modules.addbook.states.AddBookUiState
import com.istudio.code.presentation.modules.addbook.states.AddBookViewEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddBookVm @Inject constructor(
    private var addBookUseCases: AddBookUseCases
) : BaseViewModel<Unit>() {

    override fun setupPrerequisites(params: Unit) = Unit

    // Holds the data of all the widgets in the view
    var viewState by mutableStateOf(AddBookUiState())
        private set

    // View model sets this state, Composable observes this state
    private val _uiEvent = Channel<AddBookResponseEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()


    /** <************> UI Action is invoked from composable <************> **/
    fun onEvent(event: AddBookViewEvent) {
        viewModelScope.launch {
            when (event) {
                // Invoked when onCategory change of ExposedDropdownMenuBox
                is AddBookViewEvent.SetCategory -> {
                    viewState = viewState.copy(category = event.category)
                }
                // Invoked when description is set on text change of OutlinedTextField
                is AddBookViewEvent.SetDescription -> {
                    viewState = viewState.copy(description = event.description)
                }
                // Invoked when title is set on text change of OutlinedTextField
                is AddBookViewEvent.SetTitle -> {
                    viewState = viewState.copy(title = event.title)
                }
                // Invoked when Add button is clicked
                is AddBookViewEvent.AddBookViewClick -> {
                    if (validateAddBookAction()) {
                        // Success validation is notified to the composable
                        // Create a new entry for the book in database
                        createBook()
                    } else {
                        // <------------------- Failure ------------------->
                        // Check if title is empty
                        if (!validateTitle()) {
                            // Notify error state to the title filed
                            _uiEvent.send(AddBookResponseEvent.TitleFieldError)
                        } else if (!validateDescription()) {
                            // Notify error state to the description filed
                            _uiEvent.send(AddBookResponseEvent.DescriptionFieldError)
                        } else if (!validateCategory()) {
                            // Notify error state to the category filed
                            _uiEvent.send(AddBookResponseEvent.CategoryFieldError)
                        }
                    }
                }

                is AddBookViewEvent.SetIsCategoryError -> {
                    viewState = viewState.copy(isCategoryError = event.isCategoryError)
                }

                is AddBookViewEvent.SetIsDescriptionError -> {
                    viewState = viewState.copy(isDescriptionError = event.isDescriptionError)
                }

                is AddBookViewEvent.SetIsExpanded -> {
                    viewState = viewState.copy(isExpanded = event.isExpanded)
                }

                is AddBookViewEvent.SetIsTitleError -> {
                    viewState = viewState.copy(isTitleError = event.isTitleError)
                }

                is AddBookViewEvent.SetLaunchedEffectState -> {
                    viewState = viewState.copy(launchedEffectState = event.launchedEffectState)
                }

                is AddBookViewEvent.SetGenreList -> {
                    viewState = viewState.copy(genreList = event.genreList)
                }

                else -> {}
            }
        }
    }

    /** <************> UI Action is invoked from composable <************> **/

    /** <*********************> Use case invocations <*******************> **/

    /**
     * Validate All the fields
     */
    private fun validateAddBookAction(): Boolean {

        val input = AddBookAllInputs(
            title = viewState.title, description = viewState.description,
            category = viewState.category
        )

        addBookUseCases.validateAllInputs.invoke(input).onSuccess { return it.successful }
            .onFailure { return false }

        return false
    }

    /**
     * Validate title
     */
    private fun validateTitle(): Boolean {

        val input = AddBookTitleInput(title = viewState.title)

        addBookUseCases.validateTitle.invoke(input).onSuccess { return it.successful }
            .onFailure { return false }

        return false
    }

    /**
     * Validate category
     */
    private fun validateCategory(): Boolean {
        val input = AddBookCategoryInput(category = viewState.category)

        addBookUseCases.validateCategory.invoke(input).onSuccess { return it.successful }
            .onFailure { return false }

        return false
    }

    /**
     * Validate description
     */
    private fun validateDescription(): Boolean {
        val input = AddBookDescriptionInput(description = viewState.description)

        addBookUseCases.validateDescription.invoke(input).onSuccess { return it.successful }
            .onFailure { return false }

        return false
    }


    fun insertGenreToDb(bookCategories: Array<String>): Boolean {
        addBookUseCases.addGenreDataUseCase.invoke(bookCategories)
            .onSuccess {
                // Categories are inserted
                return it;
            }.onFailure {
                // Category insertion is failure
                viewModelScope.launch {
                    useCaseError(UseCaseResult.Error(Exception(it)))
                }
                return false;
            }
        return false;
    }

    fun retrieveGenreToDb(): List<String> {
        addBookUseCases.retrieveGenreDataUseCase.invoke()
            .onSuccess {
                // Categories are inserted
                return it;
            }.onFailure {
                // Category insertion is failure
                viewModelScope.launch {
                    useCaseError(UseCaseResult.Error(Exception(it)))
                }
                return emptyList();
            }
        return emptyList();
    }

    /** <*********************> Use case invocations <*******************> **/


    /**
     * Creating a new book entry in the local database
     */
    fun createBook() {

        val input = AddBookAllInputs(
            title = viewState.title, description = viewState.description,
            category = viewState.category
        )

        launchUseCase(catchException {
            Log.d("Error", it.message.toString())
            //_viewState.value = LoginViewState.ViewStateException(exception = "Validation Failed")
        }) {
            when (val result = addBookUseCases.addBookUseCase.invoke(input)) {
                is Result.Success -> {
                    if(result.value.data==true){
                        _uiEvent.send(AddBookResponseEvent.AddBookSuccess)
                    }
                }
                is Result.Error -> {
                    useCaseError(UseCaseResult.Error(Exception(result.exception)))
                }
                is Result.Loading -> {}
            }
        }

    }





    /** ********************************* DISPLAY MESSAGES ****************************************/
    /**
     * ERROR HANDLING:
     * Displaying messages to the snack-bar
     */
    private suspend fun useCaseErrorMessage(result: UiText?) {
        result?.let { _uiEvent.send(AddBookResponseEvent.ShowSnackBar(it.toString())) }
    }

    /**
     * ERROR HANDLING:
     * For the Use cases
     */
    private suspend fun useCaseError(result: UseCaseResult.Error) {
        val uiEvent = UiText.DynamicString(result.exception.message.toString())
        _uiEvent.send(AddBookResponseEvent.ShowSnackBar(uiEvent.text))
    }

    /** ********************************* DISPLAY MESSAGES ****************************************/


}