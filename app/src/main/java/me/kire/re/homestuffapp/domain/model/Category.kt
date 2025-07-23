package me.kire.re.homestuffapp.domain.model

import androidx.annotation.DrawableRes
import me.kire.re.homestuffapp.R
import me.kire.re.homestuffapp.presentation.navigation.Route

data class Category(
    val name: String,
    val route: String? = null,
    @DrawableRes
    val icon: Int,
    val itemsCount: Int = 0,
)

val categories = listOf(
    Category(
        name = "Food",
        route = Route.NourishmentScreen.route,
        icon = R.drawable.ic_food,
        itemsCount = 12,
    ),
    Category(
        name = "Bathroom",
        icon = R.drawable.ic_bathroom,
        itemsCount = 8,
    ),
    Category(
        name = "Laundry",
        icon = R.drawable.ic_laundry,
        itemsCount = 5,
    ),
    Category(
        name = "Cleaning",
        icon = R.drawable.ic_cleaning,
        itemsCount = 15,
    ),
    Category(
        name = "Office",
        icon = R.drawable.ic_office,
        itemsCount = 10,
    ),
    Category(
        name = "Pets",
        icon = R.drawable.ic_pets,
        itemsCount = 7,
    ),
)
