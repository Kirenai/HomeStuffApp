package me.kire.re.homestuffapp.presentation.home.category

import me.kire.re.homestuffapp.domain.model.Category

sealed class CategoryFormEvent {
    data class OnSaveCategory(val category: Category): CategoryFormEvent()
    data object ClearSuccess: CategoryFormEvent()
}