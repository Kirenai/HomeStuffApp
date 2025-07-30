package me.kire.re.homestuffapp.presentation.nourishment

import me.kire.re.homestuffapp.domain.model.Nourishment

data class ProductState(
    val searchText: String = "",
    val isSorted: Boolean = false,
    val nourishments: List<Nourishment> = emptyList(),
    val inStockNourishments: List<Nourishment> = emptyList(),
    val outOfStockNourishments: List<Nourishment> = emptyList()
) {
    val filteredNourishments: List<Nourishment>
        get() = if (searchText.isBlank()) {
            nourishments
        } else {
            nourishments.filter { nourishment ->
                nourishment.name.contains(searchText, ignoreCase = true)
            }
        }
}
