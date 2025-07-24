package me.kire.re.homestuffapp.domain.model

class CategoryWithItemCount(
    val categoryId: Long,
    val name: String,
    val itemsCount: Int,
)

val categories = listOf(
    CategoryWithItemCount(
        categoryId = 1L,
        name = "Food",
        itemsCount = 12,
    ),
    CategoryWithItemCount(
        categoryId = 2L,
        name = "Bathroom",
        itemsCount = 8,
    ),
    CategoryWithItemCount(
        categoryId = 3L,
        name = "Laundry",
        itemsCount = 5,
    ),
    CategoryWithItemCount(
        categoryId = 4L,
        name = "Cleaning",
        itemsCount = 15,
    ),
    CategoryWithItemCount(
        categoryId = 5L,
        name = "Office",
        itemsCount = 10,
    ),
    CategoryWithItemCount(
        categoryId = 6L,
        name = "Pets",
        itemsCount = 7,
    ),
)
