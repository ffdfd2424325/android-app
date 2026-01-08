package com.example.plumbingstore.data.repository

import com.example.plumbingstore.data.local.ProductDao
import com.example.plumbingstore.data.model.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepository @Inject constructor(
    private val productDao: ProductDao
) {
    fun getAllProducts(): Flow<List<Product>> = productDao.getAllProducts()

    fun getProductsByCategory(category: String): Flow<List<Product>> =
        productDao.getProductsByCategory(category)

    fun searchProducts(query: String): Flow<List<Product>> =
        productDao.searchProducts(query)

    fun getFavoriteProducts(): Flow<List<Product>> =
        productDao.getFavoriteProducts()

    suspend fun insertProduct(product: Product) =
        productDao.insertProduct(product)

    suspend fun insertProducts(products: List<Product>) =
        productDao.insertProducts(products)

    suspend fun updateProduct(product: Product) =
        productDao.updateProduct(product)

    suspend fun deleteProduct(product: Product) =
        productDao.deleteProduct(product)

    suspend fun updateFavoriteStatus(productId: String, isFavorite: Boolean) =
        productDao.updateFavoriteStatus(productId, isFavorite)
} 