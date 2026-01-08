package com.example.plumbingstore.ui.screens.product

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.plumbingstore.data.model.Product

/**
 * Экран списка товаров
 * Отображает список товаров с возможностью поиска и фильтрации
 */
@Composable
fun ProductListScreen(
    navController: NavController,
    viewModel: ProductViewModel = hiltViewModel()
) {
    val products by viewModel.products.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val selectedCategory by viewModel.selectedCategory.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        // Поле поиска
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { viewModel.updateSearchQuery(it) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            placeholder = { Text("Поиск товаров") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Поиск") }
        )

        // Список товаров
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            items(products) { product ->
                ProductItem(
                    product = product,
                    onFavoriteClick = { viewModel.toggleFavorite(product) }
                )
            }
        }
    }
}

/**
 * Компонент отображения товара
 * @param product товар для отображения
 * @param onFavoriteClick обработчик нажатия на кнопку избранного
 */
@Composable
fun ProductItem(
    product: Product,
    onFavoriteClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Информация о товаре
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = product.description,
                    style = MaterialTheme.typography.body2
                )
                Text(
                    text = "${product.price} ₽",
                    style = MaterialTheme.typography.subtitle1
                )
            }
            // Кнопка избранного
            IconButton(onClick = onFavoriteClick) {
                Icon(
                    imageVector = if (product.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Добавить в избранное",
                    tint = if (product.isFavorite) MaterialTheme.colors.primary else MaterialTheme.colors.onSurface
                )
            }
        }
    }
} 