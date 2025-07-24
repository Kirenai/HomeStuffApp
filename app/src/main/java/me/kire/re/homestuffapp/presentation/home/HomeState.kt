package me.kire.re.homestuffapp.presentation.home

import me.kire.re.homestuffapp.domain.model.CategoryWithItemCount

data class HomeState(
    val searchText: String = "",
    private val categories: List<CategoryWithItemCount> = emptyList(),
    val error: String? = null,
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