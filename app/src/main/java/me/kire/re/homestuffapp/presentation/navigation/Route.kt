package me.kire.re.homestuffapp.presentation.navigation

sealed class Route(
    val route: String
) {
    data object HomeStuffNavigation : Route(route = "homeStuffNavigation")

    data object HomeStuffNavigator : Route(route = "homeStuffNavigator")

    data object MainRoute : Route(route = "mainRoute")

    data object HomeScreen : Route(route = "homeScreen")

    data object NourishmentScreen : Route(route = "nourishmentScreen")

    data object NourishmentFormScreen : Route(route = "nourishmentFormScreen")

    data object DetailsScreen : Route(route = "detailsScreen")

    data object ShoppingScreen : Route(route = "shoppingScreen")

    data object ShoppingEditScreen : Route(route = "shoppingEditScreen")
}