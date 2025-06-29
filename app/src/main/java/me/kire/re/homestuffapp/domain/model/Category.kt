package me.kire.re.homestuffapp.domain.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import me.kire.re.homestuffapp.R

data class Category(
    val name: String,
    @DrawableRes
    val icon: Int,
    val size: Int = 0,
    val iconWidth: Dp,
    val iconHeight: Dp,
)

val categories = listOf(
    Category(
        name = "Food",
        icon = R.drawable.ic_food,
        size = 12,
        iconWidth = 24.dp,
        iconHeight = 24.dp
    ),
    Category(
        name = "Bathroom",
        icon = R.drawable.ic_bathroom,
        size = 8,
        iconWidth = 21.dp,
        iconHeight = 18.dp
    ),
    Category(
        name = "Laundry",
        icon = R.drawable.ic_laundry,
        size = 5,
        iconWidth = 24.dp,
        iconHeight = 24.dp
    ),
    Category(
        name = "Cleaning",
        icon = R.drawable.ic_cleaning,
        size = 15,
        iconWidth = 24.dp,
        iconHeight = 24.dp
    ),
    Category(
        name = "Office",
        icon = R.drawable.ic_office,
        size = 10,
        iconWidth = 24.dp,
        iconHeight = 24.dp
    ),
    Category(
        name = "Pets",
        icon = R.drawable.ic_pets,
        size = 7,
        iconWidth = 24.dp,
        iconHeight = 24.dp
    ),
)
