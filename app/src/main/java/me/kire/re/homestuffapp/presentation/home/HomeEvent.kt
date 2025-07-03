package me.kire.re.homestuffapp.presentation.home

sealed class HomeEvent {
    data class OnSearchTextChange(val newText: String) : HomeEvent()
}