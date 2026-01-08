package com.example.plumbingstore.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.plumbingstore.ui.screens.auth.LoginScreen
import com.example.plumbingstore.ui.screens.auth.RegisterScreen
import com.example.plumbingstore.ui.screens.cart.CartScreen
import com.example.plumbingstore.ui.screens.favorites.FavoritesScreen
import com.example.plumbingstore.ui.screens.home.HomeScreen
import com.example.plumbingstore.ui.screens.product.ProductListScreen
import com.example.plumbingstore.ui.screens.profile.ProfileScreen
import com.example.plumbingstore.ui.screens.sharedcart.SharedCartScreen

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Register : Screen("register")
    object Home : Screen("home")
    object ProductList : Screen("products")
    object Cart : Screen("cart")
    object Favorites : Screen("favorites")
    object Profile : Screen("profile")
    object SharedCart : Screen("shared_cart")
}

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(Screen.Login.route) {
            LoginScreen(navController = navController)
        }
        composable(Screen.Register.route) {
            RegisterScreen(navController = navController)
        }
        composable(Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(Screen.ProductList.route) {
            ProductListScreen(navController = navController)
        }
        composable(Screen.Cart.route) {
            CartScreen(navController = navController)
        }
        composable(Screen.Favorites.route) {
            FavoritesScreen(navController = navController)
        }
        composable(Screen.Profile.route) {
            ProfileScreen(navController = navController)
        }
        composable(Screen.SharedCart.route) {
            SharedCartScreen(navController = navController)
        }
    }
} 