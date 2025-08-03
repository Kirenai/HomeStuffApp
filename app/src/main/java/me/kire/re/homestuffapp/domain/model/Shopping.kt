package me.kire.re.homestuffapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Shopping(
    val shoppingId: String? = null,
    val itemName: String,
    val store: String? = null,
    val price: String? = null,
    val quantity: String? = null,
) : Parcelable

val shoppingMock = listOf(
    Shopping(
        shoppingId = "1",
        itemName = "Laundry Detergent",
        store = "SuperMarket",
        price = "$12.99",
        quantity = "2"
    ),
    Shopping(
        shoppingId = "2",
        itemName = "Dish Soap",
        store = "Grocery Depot",
        price = "$3.49",
        quantity = "1"
    ),
    Shopping(
        shoppingId = "3",
        itemName = "Toilet Paper",
        store = "Discount Warehouse",
        price = "$18.75",
        quantity = "3"
    ),
    Shopping(
        shoppingId = "4",
        itemName = "Paper Towels",
        store = "SuperMarket",
        price = "$6.29",
        quantity = "3"
    ),
    Shopping(
        shoppingId = "5",
        itemName = "Trash Bags",
        store = "Discount Warehouse",
        price = "$9.99",
        quantity = "2"
    ),
)