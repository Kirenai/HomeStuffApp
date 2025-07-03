package me.kire.re.homestuffapp.presentation.home

import me.kire.re.homestuffapp.domain.model.Category

data class HomeState(
    val searchText: String = "",
    private val categories: List<Category> = emptyList()
) {
    val filteredCategories
        get() = if (searchText.isBlank()) {
            categories
        } else {
            categories.filter { category ->
                category.name.contains(searchText, ignoreCase = true)
            }
        }
}