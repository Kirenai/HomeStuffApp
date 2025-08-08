package me.kire.re.homestuffapp.presentation.home

sealed class HomeEvent {
    data class OnSearchTextChange(val newText: String) : HomeEvent()
    data object OnErrorDismissed : HomeEvent()
    data class OnError(val message: String) : HomeEvent()
    data class OnDeleteCategory(val categoryId: Long) : HomeEvent()
}