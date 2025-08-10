package me.re.homestuffapp.presentation.homestuff_navigator

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import me.re.homestuffapp.domain.model.Product
import me.re.homestuffapp.domain.model.Shopping
import me.re.homestuffapp.presentation.details.DetailsScreen
import me.re.homestuffapp.presentation.details.DetailsViewModel
import me.re.homestuffapp.presentation.home.HomeScreen
import me.re.homestuffapp.presentation.home.HomeViewModel
import me.re.homestuffapp.presentation.home.category.CategoryFormScreen
import me.re.homestuffapp.presentation.home.category.CategoryFormViewModel
import me.re.homestuffapp.presentation.homestuff_navigator.components.BottomBar
import me.re.homestuffapp.presentation.homestuff_navigator.components.TopAppBar
import me.re.homestuffapp.presentation.navigation.Route
import me.re.homestuffapp.presentation.product.ProductScreen
import me.re.homestuffapp.presentation.product.ProductViewModel
import me.re.homestuffapp.presentation.product.form.ProductFormScreen
import me.re.homestuffapp.presentation.product.form.ProductFormViewModel
import me.re.homestuffapp.presentation.shopping.ShoppingScreen
import me.re.homestuffapp.presentation.shopping.ShoppingViewModel
import me.re.homestuffapp.presentation.shopping.form.ShoppingEditScreen
import me.re.homestuffapp.util.Constants.KEY_CATEGORY_ERROR
import me.re.homestuffapp.util.Constants.KEY_NOURISHMENT
import me.re.homestuffapp.util.Constants.KEY_SHOPPING

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null
)

@Composable
fun HomeStuffNavigator() {
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
        Route.NourishmentScreen.route -> "Product"
        Route.DetailsScreen.route -> "Details"
        Route.NourishmentFormScreen.route -> "Product Form"
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
                    println("selectedItem = $selectedItem")
//                    if (selectedItem == 4) {
//                        navController.navigate(Route.HomeScreen.route)
//                    } else navController.popBackStack()
                    when (selectedItem) {
                        3 -> {
                            val categoryId = navController.currentBackStackEntry
                                ?.arguments?.getString("categoryId")?.toLongOrNull()
                            println("categoryId TAB.navigationUp = $categoryId")
                            if (categoryId != null) {
                                navController.popBackStack(
                                    Route.NourishmentScreen.createRoute(
                                        categoryId
                                    ),
                                    inclusive = false
                                )
                            } else {
                                navController.popBackStack()
                            }
                        }

                        4 -> navController.navigate(Route.HomeScreen.route)
                        else -> navController.popBackStack()
                    }
                },
                isHomeScreen = selectedItem == 0,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
            )
        },
        bottomBar = {
            if (!isBottomBarVisible) return@Scaffold
            BottomBar(
                navController = navController,
                selectedItem = selectedItem
            )
        },
        floatingActionButton = {
            when (navController.currentDestination?.route) {
                Route.NourishmentScreen.route -> {
                    val categoryId = navController.currentBackStackEntry
                        ?.arguments?.getString("categoryId")?.toLongOrNull()
                    println("categoryId FAB = $categoryId")
                    categoryId?.let {
                        FloatingActionButton(
                            onClick = {
                                navController.navigate(
                                    Route.NourishmentFormScreen.createRoute(
                                        categoryId = it
                                    )
                                )
                            },
                            containerColor = MaterialTheme.colorScheme.onPrimary,
                            contentColor = MaterialTheme.colorScheme.primary,
                        ) {
                            Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
                        }
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
                    ?.getLiveData(KEY_CATEGORY_ERROR)
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
                            ?.set(KEY_CATEGORY_ERROR, null)
                    }
                )
            }
            composable(
                route = Route.NourishmentScreen.route,
                arguments = listOf(
                    navArgument("categoryId") {
                        type = NavType.StringType
                        nullable = true
                        defaultValue = null
                    })
            ) { backStackEntry ->
                val viewModel: ProductViewModel = hiltViewModel()
                val products: LazyPagingItems<Product> =
                    viewModel.nourishments.collectAsLazyPagingItems()
                ProductScreen(
                    products = products,
                    navigateToDetails = { product ->
                        navigateToDetails(
                            navController = navController,
                            product = product
                        )
                    },
                    navigateToSearch = {},
                    event = viewModel::onEvent
                )
            }
            composable(
                route = Route.NourishmentFormScreen.route,
                arguments = listOf(
                    navArgument("categoryId") {
                        type = NavType.StringType
                        nullable = true
                        defaultValue = null
                    })
            ) { backStackEntry ->
                val categoryId = backStackEntry.arguments?.getString("categoryId")?.toLongOrNull()
                println("categoryId NFS = $categoryId")
                val viewModel: ProductFormViewModel = hiltViewModel()
                categoryId?.let {id ->
                    ProductFormScreen(
                        event = viewModel::onEvent,
                        state = viewModel.state.collectAsState().value,
                        categoryId = id,
                        navigateUp = {
                            navController.navigateUp()
                        }
                    )
                }
            }
            composable(route = Route.DetailsScreen.route) {
                val parentEntry = remember(navController.currentBackStackEntry) {
                    navController.getBackStackEntry(Route.MainRoute.route)
                }
                val shoppingViewModel: ShoppingViewModel = hiltViewModel(parentEntry)

                val product = navController.previousBackStackEntry
                    ?.savedStateHandle
                    ?.get<Product?>(KEY_NOURISHMENT)

                product?.let {
                    val viewModel: DetailsViewModel = hiltViewModel()
                    val isAlreadyAdded =
                        shoppingViewModel.shoppingList.any { shopping -> shopping.itemName == product.name }
                    val charData by viewModel.charData.collectAsState()
                    val months by viewModel.months.collectAsState()
                    val lastTwoPurchases by viewModel.lastTwoPurchases.collectAsState()
                    DetailsScreen(
                        product = product,
                        navigateToShopping = {
                            navController.navigate(Route.ShoppingScreen.route)
                        },
                        isAlreadyAdded = isAlreadyAdded,
                        event = shoppingViewModel::onEvent,
                        lastTwoPurchases = lastTwoPurchases,
                        charData = charData,
                        months = months
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
                navController.previousBackStackEntry?.savedStateHandle?.get<Shopping>(KEY_SHOPPING)
                    ?.let { shopping ->
                        val parentEntry = remember(navController.currentBackStackEntry) {
                            navController.getBackStackEntry(Route.MainRoute.route)
                        }
                        val viewModel: ShoppingViewModel = hiltViewModel(parentEntry)
                        ShoppingEditScreen(
                            shopping = shopping,
                            event = viewModel::onEvent,
                            navigateUp = {
                                navController.popBackStack()
                            }
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
                                ?.set(KEY_CATEGORY_ERROR, error)
                        }

                        navController.popBackStack()
                    },
                    state = viewModel.state.collectAsState().value
                )
            }
        }
    }
}

fun navigateToTab(navController: NavHostController, route: String) {
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
    product: Product
) {
    navController.currentBackStackEntry?.savedStateHandle?.set(KEY_NOURISHMENT, product)
    product.productId?.let {
        navController.navigate(
            route = Route.DetailsScreen.createRoute(productId = product.productId)
        )
    }
}

private fun navigateToShoppingEdit(
    navController: NavHostController,
    shopping: Shopping
) {
    navController.currentBackStackEntry?.savedStateHandle?.set(KEY_SHOPPING, shopping)
    navController.navigate(
        route = Route.ShoppingEditScreen.route
    )
}
