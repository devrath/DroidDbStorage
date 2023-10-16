package com.istudio.code.ui.modules.addbook

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddBookVm @Inject constructor() : ViewModel() {

    var viewState by mutableStateOf(AddBookUiState())
        private set



    /** ************** Functions to set data in view-Model *************** **/
    /**
     * Here when the text change happens in the composable for title, It is saved in view-state.
     *
     * @param title
     */
    fun setTitle(title:String){
        viewState = viewState.copy(title = title)
    }

    /**
     * Here when the text change happens in the composable for title, It is saved in view-state.
     *
     * @param description
     */
    fun setDescription(description:String){
        viewState = viewState.copy(description = description)
    }

    /**
     * Here when the category is changed it is updated in hte view-state
     *
     * @param category
     */
    fun setCategory(category:String){
        viewState = viewState.copy(category = category)
    }
    /** ************** Functions to set data in view-Model *************** **/

    /** ********* Validation Functions invoked from composable *********** **/
    /**
     * TITLE , DESCRIPTION , CATEGORY-SELECTION - VALIDATION
     *
     * It returns true if all the values are entered else it returns false
     *
     * @param title This is the title of the book
     * @param description This is the description of the book
     * @param defaultText This is the text which is present before user makes a selection in the drop-down
     * @param selectedCategory This is the selection the user makes that will be different from defaultText
     */
    fun validateAddBookAction(): Boolean {
        return validateIsTitleEntered(viewState.title) &&
                validateIsDescriptionEntered(viewState.description) &&
                validateIsCategorySelected(viewState.category)
    }

    /**
     * CATEGORY VALIDATION
     *
     * <1> category selected --> Returns true
     * <2> category is not selected --> Returns false
     *
     * @param defaultText This is the text which is present before user makes a selection in the drop-down
     * @param selectedCategory This is the selection the user makes that will be different from defaultText
     */
    private fun validateIsCategorySelected(selectedCategory: String): Boolean {
        return selectedCategory.isNotEmpty()
    }

    /**
     * DESCRIPTION VALIDATION
     *
     * <1> description entered --> Returns true
     * <2> description not entered --> Returns false
     *
     * @param description This is the description of the book
     *
     */
    private fun validateIsDescriptionEntered(description: String): Boolean {
        return description.isNotEmpty()
    }

    /**
     * TITLE VALIDATION
     *
     * <1> title entered --> Returns true
     * <2> title not entered --> Returns false
     *
     * @param title This is the title of the book
     */
    private fun validateIsTitleEntered(title: String): Boolean {
        return title.isNotEmpty()
    }
    /** ********* Validation Functions invoked from composable *********** **/



}