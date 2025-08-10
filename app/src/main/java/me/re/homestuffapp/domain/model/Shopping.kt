package me.re.homestuffapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import me.re.homestuffapp.domain.model.enums.UnitType

@Parcelize
data class Shopping(
    val shoppingId: Long? = null,
    val itemName: String,
    val store: String? = null,
    val price: String? = null,
    val quantity: String? = null,
    val unit: UnitType? = null,
    val productId: Long? = null,
) : Parcelable

val shoppingMock = listOf(
    Shopping(
        shoppingId = 1,
        itemName = "Laundry Detergent",
        store = "SuperMarket",
        price = "$12.99",
        quantity = "2",
        productId = 1L
    ),
    Shopping(
        shoppingId = 2,
        itemName = "Dish Soap",
        store = "Grocery Depot",
        price = "$3.49",
        quantity = "1",
        productId = 2L
    ),
    Shopping(
        shoppingId = 3,
        itemName = "Toilet Paper",
        store = "Discount Warehouse",
        price = "$18.75",
        quantity = "3",
        productId = 3L
    ),
    Shopping(
        shoppingId = 4,
        itemName = "Paper Towels",
        store = "SuperMarket",
        price = "$6.29",
        quantity = "3",
        productId = 4L
    ),
    Shopping(
        shoppingId = 5,
        itemName = "Trash Bags",
        store = "Discount Warehouse",
        price = "$9.99",
        quantity = "2",
        productId = 5L
    ),
)