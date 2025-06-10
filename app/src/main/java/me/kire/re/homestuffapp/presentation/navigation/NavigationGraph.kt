package me.kire.re.homestuffapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import me.kire.re.homestuffapp.presentation.homestuff_navigator.HomeStuffNavigator

@Composable
fun NavigationGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Route.HomeStuffNavigation.route) {
        navigation(
            route = Route.HomeStuffNavigation.route,
            startDestination = Route.HomeStuffNavigator.route
        ) {
            composable(
                route = Route.HomeStuffNavigator.route,
            ) {
                HomeStuffNavigator()
            }
        }
    }
}