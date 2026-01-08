package com.example.plumbingstore.ui.screens.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plumbingstore.data.model.Product
import com.example.plumbingstore.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel для работы с товарами
 * Управляет состоянием списка товаров, поиском и фильтрацией
 */
@HiltViewModel
class ProductViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {

    // Поисковый запрос
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    // Выбранная категория
    private val _selectedCategory = MutableStateFlow<String?>(null)
    val selectedCategory: StateFlow<String?> = _selectedCategory.asStateFlow()

    // Список товаров с учетом поиска и фильтрации
    val products: StateFlow<List<Product>> = combine(
        searchQuery,
        selectedCategory
    ) { query, category ->
        when {
            query.isNotEmpty() -> productRepository.searchProducts(query)
            category != null -> productRepository.getProductsByCategory(category)
            else -> productRepository.getAllProducts()
        }
    }.flatMapLatest { it }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    /**
     * Обновить поисковый запрос
     * @param query новый поисковый запрос
     */
    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    /**
     * Обновить выбранную категорию
     * @param category новая категория
     */
    fun updateSelectedCategory(category: String?) {
        _selectedCategory.value = category
    }

    /**
     * Переключить статус избранного для товара
     * @param product товар для изменения статуса
     */
    fun toggleFavorite(product: Product) {
        viewModelScope.launch {
            productRepository.updateFavoriteStatus(
                productId = product.id,
                isFavorite = !product.isFavorite
            )
        }
    }
} 