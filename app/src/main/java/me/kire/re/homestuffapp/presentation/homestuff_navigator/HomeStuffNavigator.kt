package me.kire.re.homestuffapp.presentation.homestuff_navigator

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import me.kire.re.homestuffapp.presentation.home.HomeScreen
import me.kire.re.homestuffapp.presentation.nourishment.NourishmentScreen

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null
)

@Composable
fun HomeStuffNavigator() {
    val items = listOf(
        BottomNavigationItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            hasNews = false
        ),
        BottomNavigationItem(
            title = "Nourishment",
            selectedIcon = Icons.Filled.Info,
            unselectedIcon = Icons.Outlined.Info,
            hasNews = false
        )
    )
    var selectedItem by rememberSaveable {
        mutableIntStateOf(0)
    }

    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value

    selectedItem = when (backStackState?.destination?.route) {
        "homeScreen" -> 0
        "nourishmentScreen" -> 1
        else -> 0
    }


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar(
                modifier = Modifier
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
            ) {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = index == selectedItem,
                        onClick = {
                            selectedItem = index
                            println(index)
                            when (index) {
                                0 -> navigateToTab(
                                    navController = navController,
                                    route = "homeScreen"
                                )
                                1 -> navigateToTab(
                                    navController = navController,
                                    route = "nourishmentScreen"
                                )
                            }
                        },
                        label = {
                            Text(text = item.title)
                        },
                        icon = {
                            BadgedBox(
                                badge = {
                                    if (item.badgeCount != null) {
                                        Badge {
                                            Text(text = item.badgeCount.toString())
                                        }
                                    } else if (item.hasNews) {
                                        Badge()
                                    }
                                }
                            ) {
                                Icon(
                                    imageVector = if (index == selectedItem) {
                                        item.selectedIcon
                                    } else item.unselectedIcon,
                                    contentDescription = item.title
                                )
                            }
                        }
                    )
                }
            }
        }
    ) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = "nourishmentScreen",
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(route = "homeScreen") {
                HomeScreen(navigateToNourishment = {
                    navigateToTab(
                        navController = navController,
                        route = "nourishmentScreen"
                    )
                })
            }
            composable(route = "nourishmentScreen") {
                NourishmentScreen(
                    navigateToHome = {
                        navigateToTab(
                            navController = navController,
                            route = "homeScreen"
                        )
                    }
                )
            }
        }
    }
}

private fun navigateToTab(navController: NavHostController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { screenRoute ->
            popUpTo(screenRoute) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}
