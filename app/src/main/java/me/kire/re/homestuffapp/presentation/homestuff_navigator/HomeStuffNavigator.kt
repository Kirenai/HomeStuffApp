package me.kire.re.homestuffapp.presentation.homestuff_navigator

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.ShoppingCart
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
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
import me.kire.re.homestuffapp.domain.model.Shopping
import me.kire.re.homestuffapp.presentation.details.DetailsScreen
import me.kire.re.homestuffapp.presentation.home.HomeScreen
import me.kire.re.homestuffapp.presentation.home.HomeViewModel
import me.kire.re.homestuffapp.presentation.homestuff_navigator.components.TopAppBar
import me.kire.re.homestuffapp.presentation.navigation.Route
import me.kire.re.homestuffapp.presentation.nourishment.NourishmentScreen
import me.kire.re.homestuffapp.presentation.nourishment.NourishmentViewModel
import me.kire.re.homestuffapp.presentation.nourishment.form.NourishmentFormScreen
import me.kire.re.homestuffapp.presentation.nourishment.form.NourishmentFormViewModel
import me.kire.re.homestuffapp.presentation.shopping.ShoppingScreen
import me.kire.re.homestuffapp.presentation.shopping.ShoppingViewModel

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
            title = "Lists",
            selectedIcon = Icons.AutoMirrored.Filled.List,
            unselectedIcon = Icons.AutoMirrored.Outlined.List,
            hasNews = false
        ),
        BottomNavigationItem(
            title = "Nourishment",
            selectedIcon = Icons.Filled.Info,
            unselectedIcon = Icons.Outlined.Info,
            hasNews = false
        ),
        BottomNavigationItem(
            title = "Shopping",
            selectedIcon = Icons.Filled.ShoppingCart,
            unselectedIcon = Icons.Outlined.ShoppingCart,
            hasNews = false
        ),
    )

    var selectedItem by rememberSaveable {
        mutableIntStateOf(0)
    }

    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value

    selectedItem = when (backStackState?.destination?.route) {
        Route.HomeScreen.route -> 0
        Route.NourishmentScreen.route -> 1
        Route.DetailsScreen.route -> 2
        Route.NourishmentFormScreen.route -> 3
        Route.ShoppingScreen.route -> 4
        else -> 0
    }

    val isBottomBarVisible = remember(key1 = backStackState) {
        backStackState?.destination?.route == Route.HomeScreen.route
                || backStackState?.destination?.route == Route.NourishmentScreen.route
    }

    var title by remember {
        mutableStateOf(backStackState?.destination?.route ?: "")
    }

    title = when (backStackState?.destination?.route) {
        Route.HomeScreen.route -> "Home"
        Route.NourishmentScreen.route -> "Nourishment"
        Route.DetailsScreen.route -> "Details"
        Route.NourishmentFormScreen.route -> "Nourishment Form"
        Route.ShoppingScreen.route -> "Shopping"
        else -> ""
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = title,
                navigateUp = {
                    if (selectedItem == 4) {
                        navController.navigate(Route.HomeScreen.route)
                    } else navController.navigateUp()
                },
                isHomeScreen = selectedItem == 0,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
            )
        },
        bottomBar = {
            if (!isBottomBarVisible) return@Scaffold
            NavigationBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .drawBehind {
                        val strokeWidth = 1.dp.toPx()
                        val y = 0f

                        drawLine(
                            color = Color.LightGray,
                            start = Offset(0f, y),
                            end = Offset(size.width, y),
                            strokeWidth = strokeWidth
                        )
                    },
                containerColor = MaterialTheme.colorScheme.background,
            ) {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = index == selectedItem,
                        onClick = {
                            selectedItem = index
                            when (index) {
                                0 -> navigateToTab(
                                    navController = navController,
                                    route = Route.HomeScreen.route
                                )

                                1 -> navigateToTab(
                                    navController = navController,
                                    route = Route.NourishmentScreen.route
                                )

                                3 -> navigateToTab(
                                    navController = navController,
                                    route = Route.ShoppingScreen.route
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
                Route.NourishmentScreen.route -> {
                    FloatingActionButton(
                        onClick = {
                            navigateToTab(
                                navController = navController,
                                route = Route.NourishmentFormScreen.route
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
        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            route = Route.MainRoute.route,
            modifier = Modifier.padding(it)
        ) {
            composable(route = Route.HomeScreen.route) {
                val viewModel: HomeViewModel = hiltViewModel()
                HomeScreen(
                    event = viewModel::onEvent,
                    state = viewModel.state.value,
                    navigateToCategory = { route ->
                        navigateToTab(
                            navController = navController,
                            route = route
                        )
                    }
                )
            }
            composable(route = Route.NourishmentScreen.route) {
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
            composable(route = Route.NourishmentFormScreen.route) {
                val viewModel: NourishmentFormViewModel = hiltViewModel()
                NourishmentFormScreen(
                    event = viewModel::onEvent,
                    state = viewModel.state.collectAsState().value,
                )
            }
            composable(route = Route.DetailsScreen.route) {
                val parentEntry = remember(navController.currentBackStackEntry) {
                    navController.getBackStackEntry(Route.MainRoute.route)
                }
                val viewModel: ShoppingViewModel = hiltViewModel(parentEntry)

                navController.previousBackStackEntry
                    ?.savedStateHandle
                    ?.get<Nourishment?>("nourishment")
                    ?.let { nourishment ->
                        val isAlreadyAdded =
                            viewModel.shoppingList.any { shopping -> shopping.itemName == nourishment.name }
                        DetailsScreen(
                            nourishment = nourishment,
                            addToShopping = {
                                viewModel.addItem(
                                    Shopping(
                                        itemName = nourishment.name,
                                    )
                                )
                                navController.navigate(Route.ShoppingScreen.route)
                            },
                            isAlreadyAdded = isAlreadyAdded,
                        )
                    }
            }
            composable(route = Route.ShoppingScreen.route) {
                val parentEntry = remember(navController.currentBackStackEntry) {
                    navController.getBackStackEntry(Route.MainRoute.route)
                }
                val viewModel: ShoppingViewModel = hiltViewModel(parentEntry)

                ShoppingScreen(
                    viewModel.shoppingList
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

private fun navigateToDetails(
    navController: NavHostController,
    nourishment: Nourishment
) {
    navController.currentBackStackEntry?.savedStateHandle?.set("nourishment", nourishment)
    navController.navigate(
        route = Route.DetailsScreen.route
    )
}
