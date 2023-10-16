package com.istudio.code.ui.modules.addbook

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.istudio.code.ui.modules.addbook.states.AddBookResponseEvent
import com.istudio.code.ui.modules.addbook.states.AddBookUiState
import com.istudio.code.ui.modules.addbook.states.AddBookViewEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


enum class FIELD_VALIDATIONS { TITLE, DESCRIPTION, CATEGORY }

@HiltViewModel
class AddBookVm @Inject constructor() : ViewModel() {

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
                        _uiEvent.send(AddBookResponseEvent.AddBookSuccess)
                    } else {
                        // <------------------- Failure ------------------->
                        // Check if title is empty
                        if(viewState.title.isEmpty()){
                            // Notify error state to the title filed
                            _uiEvent.send(AddBookResponseEvent.TitleFieldError)
                        }else if(viewState.description.isEmpty()){
                            // Notify error state to the description filed
                            _uiEvent.send(AddBookResponseEvent.DescriptionFieldError)
                        }else if(viewState.category.isEmpty()){
                            // Notify error state to the category filed
                            _uiEvent.send(AddBookResponseEvent.CategoryFieldError)
                        }
                    }
                }
            }
        }
    }

    /** <************> UI Action is invoked from composable <************> **/

    private fun validateFields(widget: FIELD_VALIDATIONS) = when (widget) {
        // Title validation
        FIELD_VALIDATIONS.TITLE -> viewState.title.isNotEmpty()
        // Description validation
        FIELD_VALIDATIONS.DESCRIPTION -> viewState.description.isNotEmpty()
        // Category validation
        FIELD_VALIDATIONS.CATEGORY -> viewState.category.isNotEmpty()
    }

    private fun validateAddBookAction(): Boolean {
        return validateFields(FIELD_VALIDATIONS.TITLE) &&
                validateFields(FIELD_VALIDATIONS.DESCRIPTION) &&
                validateFields(FIELD_VALIDATIONS.CATEGORY)
    }

}