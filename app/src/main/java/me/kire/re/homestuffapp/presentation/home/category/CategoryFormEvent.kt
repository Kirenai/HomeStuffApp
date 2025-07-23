package me.kire.re.homestuffapp.presentation.home.category

sealed class CategoryFormEvent {
    data class OnSaveCategory(val name: String): CategoryFormEvent()
}