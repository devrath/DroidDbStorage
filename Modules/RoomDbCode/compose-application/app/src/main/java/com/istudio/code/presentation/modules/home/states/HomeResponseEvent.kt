package com.istudio.code.presentation.modules.home.states

sealed class HomeResponseEvent {
    data class ShowSnackBar(val message: String) : HomeResponseEvent()
}
