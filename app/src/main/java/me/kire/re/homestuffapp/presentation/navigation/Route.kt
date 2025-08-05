package me.kire.re.homestuffapp.presentation.navigation

sealed class Route(
    val route: String
) {
    data object HomeStuffNavigation : Route(route = "homeStuffNavigation")

    data object HomeStuffNavigator : Route(route = "homeStuffNavigator")

    data object MainRoute : Route(route = "mainRoute")

    data object HomeScreen : Route(route = "homeScreen")

    data object NourishmentScreen : Route(route = "nourishmentScreen?categoryId={categoryId}") {
        fun createRoute(categoryId: Long): String {
            return "nourishmentScreen?categoryId=$categoryId"
        }
    }

    data object NourishmentFormScreen : Route(route = "nourishmentFormScreen?categoryId={categoryId}") {
        fun createRoute(categoryId: Long): String {
            return "nourishmentFormScreen?categoryId=$categoryId"
        }
    }

    data object DetailsScreen : Route(route = "detailsScreen/{productId}") {
        fun createRoute(productId: Long): String {
            return "detailsScreen/$productId"
        }
    }

    data object ShoppingScreen : Route(route = "shoppingScreen")

    data object ShoppingEditScreen : Route(route = "shoppingEditScreen")

    data object CategoryFormScreen : Route(route = "categoryFormScreen")
}