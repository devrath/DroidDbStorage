package com.istudio.code.presentation.modules.home.states

sealed class HomeResponseEvent {
    object RefreshData : HomeResponseEvent()
    data class ShowSnackBar(val message: String) : HomeResponseEvent()
}
