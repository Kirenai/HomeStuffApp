package me.re.homestuffapp.presentation.home

import me.re.homestuffapp.domain.model.Category

sealed class HomeEvent {
    data class OnSearchTextChange(val newText: String) : HomeEvent()
    data object OnErrorDismissed : HomeEvent()
    data class OnError(val message: String) : HomeEvent()
    data class OnDeleteCategory(val categoryId: Long) : HomeEvent()
    data class OnUpdateCategoryName(val category: Category) : HomeEvent()
}