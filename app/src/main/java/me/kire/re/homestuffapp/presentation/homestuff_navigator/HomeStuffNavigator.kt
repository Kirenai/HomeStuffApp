package me.kire.re.homestuffapp.presentation.homestuff_navigator

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import me.kire.re.homestuffapp.domain.model.Nourishment
import me.kire.re.homestuffapp.presentation.details.DetailsScreen
import me.kire.re.homestuffapp.presentation.home.HomeScreen
import me.kire.re.homestuffapp.presentation.nourishment.NourishmentScreen
import me.kire.re.homestuffapp.presentation.nourishment.NourishmentViewModel
import me.kire.re.homestuffapp.presentation.nourishment.form.NourishmentFormScreen

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

    val isBottomBarVisible = remember(key1 = backStackState) {
        backStackState?.destination?.route == "homeScreen"
                || backStackState?.destination?.route == "nourishmentScreen"
    }

    val isNourishmentScreenVisible = remember(key1 = backStackState) {
        backStackState?.destination?.route == "nourishmentScreen"
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (!isBottomBarVisible) return@Scaffold
            NavigationBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
                containerColor = MaterialTheme.colorScheme.background,
                tonalElevation = 10.dp,
            ) {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = index == selectedItem,
                        onClick = {
                            selectedItem = index
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
        },
        floatingActionButton = {
            when (navController.currentDestination?.route) {
                "nourishmentScreen" -> {
                    FloatingActionButton(
                        onClick = {
                            navigateToTab(
                                navController = navController,
                                route = "nourishmentFormScreen"
                            )
                        },
                        containerColor = MaterialTheme.colorScheme.background,
                        contentColor = MaterialTheme.colorScheme.primary,
                    ) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
                    }
                }
            }
        },
        floatingActionButtonPosition = FabPosition.End,
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
                val viewModel: NourishmentViewModel = hiltViewModel()
                val nourishments: LazyPagingItems<Nourishment> =
                    viewModel.nourishments.collectAsLazyPagingItems()
                NourishmentScreen(
                    nourishments = nourishments,
                    navigateToDetails = { nourishment ->
                        navigateToDetails(
                            navController = navController,
                            nourishment = nourishment
                        )
                    },
                    navigateToSearch = {}
                )
            }
            composable(route = "nourishmentFormScreen") {
                NourishmentFormScreen(
                    navigateUp = {
                        navController.navigateUp()
                    },
                    onSave = {}
                )
            }
            composable(route = "detailsScreen") {
                navController.previousBackStackEntry
                    ?.savedStateHandle
                    ?.get<Nourishment?>("nourishment")
                    ?.let { nourishment ->
                        DetailsScreen(
                            nourishment = nourishment,
                            navigateUp = {
                                navController.navigateUp()
                            }
                        )
                    }
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

private fun navigateToDetails(
    navController: NavHostController,
    nourishment: Nourishment
) {
    navController.currentBackStackEntry?.savedStateHandle?.set("nourishment", nourishment)
    navController.navigate(
        route = "detailsScreen"
    )
}
