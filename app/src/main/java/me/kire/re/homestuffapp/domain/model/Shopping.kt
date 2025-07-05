package me.kire.re.homestuffapp.domain.model

data class Shopping(
    val itemName: String,
    val store: String? = null,
    val price: String? = null,
    val quantity: String? = null,
)

val shoppingMock = listOf(
    Shopping(
        itemName = "Laundry Detergent",
        store = "SuperMarket",
        price = "$12.99",
        quantity = "2"
    ),
    Shopping(
        itemName = "Dish Soap",
        store = "Grocery Depot",
        price = "$3.49",
        quantity = "1"
    ),
    Shopping(
        itemName = "Toilet Paper",
        store = "Discount Warehouse",
        price = "$18.75",
        quantity = "3"
    ),
    Shopping(
        itemName = "Paper Towels",
        store = "SuperMarket",
        price = "$6.29",
        quantity = "3"
    ),
    Shopping(
        itemName = "Trash Bags",
        store = "Discount Warehouse",
        price = "$9.99",
        quantity = "2"
    ),
)