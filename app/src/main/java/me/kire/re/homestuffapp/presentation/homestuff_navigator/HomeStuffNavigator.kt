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
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Home
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
import androidx.lifecycle.MutableLiveData
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
import me.kire.re.homestuffapp.presentation.home.category.CategoryFormScreen
import me.kire.re.homestuffapp.presentation.home.category.CategoryFormViewModel
import me.kire.re.homestuffapp.presentation.homestuff_navigator.components.TopAppBar
import me.kire.re.homestuffapp.presentation.navigation.Route
import me.kire.re.homestuffapp.presentation.nourishment.NourishmentScreen
import me.kire.re.homestuffapp.presentation.nourishment.NourishmentViewModel
import me.kire.re.homestuffapp.presentation.nourishment.form.NourishmentFormScreen
import me.kire.re.homestuffapp.presentation.nourishment.form.NourishmentFormViewModel
import me.kire.re.homestuffapp.presentation.shopping.ShoppingScreen
import me.kire.re.homestuffapp.presentation.shopping.ShoppingViewModel
import me.kire.re.homestuffapp.presentation.shopping.form.ShoppingEditScreen

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
        Route.ShoppingEditScreen.route -> 5
        Route.CategoryFormScreen.route -> 6
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
        Route.ShoppingEditScreen.route -> "Edit Shopping Item"
        Route.CategoryFormScreen.route -> "Create Category"
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

                                2 -> navigateToTab(
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
                        containerColor = MaterialTheme.colorScheme.onPrimary,
                        contentColor = MaterialTheme.colorScheme.primary,
                    ) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
                    }
                }

                Route.HomeScreen.route -> {
                    FloatingActionButton(
                        onClick = {
                            navigateToTab(
                                navController = navController,
                                route = Route.CategoryFormScreen.route
                            )
                        },
                        containerColor = MaterialTheme.colorScheme.onPrimary,
                        contentColor = MaterialTheme.colorScheme.primary,
                    ) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
                    }
                }
            }
        },
        floatingActionButtonPosition = FabPosition.End,
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            route = Route.MainRoute.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(route = Route.HomeScreen.route) {
                val viewModel: HomeViewModel = hiltViewModel()
                val categories by viewModel.categoriesWithCount.collectAsState()
                println("categories = $categories")
                val error: MutableLiveData<String>? = navController.currentBackStackEntry
                    ?.savedStateHandle
                    ?.getLiveData("category_error")
                HomeScreen(
                    event = viewModel::onEvent,
                    state = viewModel.state.value,
                    navigateToCategory = { categoryId ->
                        navigateToTab(
                            navController = navController,
                            route = Route.NourishmentScreen.createRoute(categoryId)
                        )
                    },
                    error = error,
                    clearCategoryError = {
                        navController.currentBackStackEntry
                            ?.savedStateHandle
                            ?.set("category_error", null)
                    }
                )
            }
            composable(route = Route.NourishmentScreen.route) { backStackEntry ->
                var categoryId = backStackEntry.arguments?.getString("categoryId")?.toLongOrNull()
                    ?: return@composable
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
                            navigateToShopping = {
                                navController.navigate(Route.ShoppingScreen.route)
                            },
                            isAlreadyAdded = isAlreadyAdded,
                            event = viewModel::onEvent
                        )
                    }
            }
            composable(route = Route.ShoppingScreen.route) {
                val parentEntry = remember(navController.currentBackStackEntry) {
                    navController.getBackStackEntry(Route.MainRoute.route)
                }
                val viewModel: ShoppingViewModel = hiltViewModel(parentEntry)

                ShoppingScreen(
                    viewModel.shoppingList,
                    navigateToEdit = { shopping ->
                        navigateToShoppingEdit(
                            navController = navController,
                            shopping = shopping
                        )
                    },
                    event = viewModel::onEvent
                )
            }
            composable(route = Route.ShoppingEditScreen.route) {
                navController.previousBackStackEntry?.savedStateHandle?.get<Shopping>("shopping")
                    ?.let { shopping ->
                        val parentEntry = remember(navController.currentBackStackEntry) {
                            navController.getBackStackEntry(Route.MainRoute.route)
                        }
                        val viewModel: ShoppingViewModel = hiltViewModel(parentEntry)
                        ShoppingEditScreen(
                            shopping = shopping,
                            event = viewModel::onEvent
                        )
                    }
            }
            composable(route = Route.CategoryFormScreen.route) {
                val viewModel: CategoryFormViewModel = hiltViewModel()
                CategoryFormScreen(
                    event = viewModel::onEvent,
                    navigateUp = { error ->
                        error.let {
                            navController.previousBackStackEntry
                                ?.savedStateHandle
                                ?.set("category_error", error)
                        }

                        navController.popBackStack()
                    },
                    state = viewModel.state.collectAsState().value
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

private fun navigateToShoppingEdit(
    navController: NavHostController,
    shopping: Shopping
) {
    navController.currentBackStackEntry?.savedStateHandle?.set("shopping", shopping)
    navController.navigate(
        route = Route.ShoppingEditScreen.route
    )
}
