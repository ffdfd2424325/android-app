package com.example.plumbingstore.data.local

import androidx.room.*
import com.example.plumbingstore.data.model.Product
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object для работы с товарами в базе данных
 * Содержит методы для CRUD операций с товарами
 */
@Dao
interface ProductDao {
    /**
     * Получить все товары
     */
    @Query("SELECT * FROM products")
    fun getAllProducts(): Flow<List<Product>>

    /**
     * Получить товары по категории
     * @param category категория товаров
     */
    @Query("SELECT * FROM products WHERE category = :category")
    fun getProductsByCategory(category: String): Flow<List<Product>>

    /**
     * Поиск товаров по названию
     * @param query поисковый запрос
     */
    @Query("SELECT * FROM products WHERE name LIKE '%' || :query || '%'")
    fun searchProducts(query: String): Flow<List<Product>>

    /**
     * Получить избранные товары
     */
    @Query("SELECT * FROM products WHERE isFavorite = 1")
    fun getFavoriteProducts(): Flow<List<Product>>

    /**
     * Добавить товар
     * @param product товар для добавления
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: Product)

    /**
     * Добавить список товаров
     * @param products список товаров для добавления
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(products: List<Product>)

    /**
     * Обновить товар
     * @param product товар для обновления
     */
    @Update
    suspend fun updateProduct(product: Product)

    /**
     * Удалить товар
     * @param product товар для удаления
     */
    @Delete
    suspend fun deleteProduct(product: Product)

    /**
     * Обновить статус избранного
     * @param productId идентификатор товара
     * @param isFavorite новый статус избранного
     */
    @Query("UPDATE products SET isFavorite = :isFavorite WHERE id = :productId")
    suspend fun updateFavoriteStatus(productId: String, isFavorite: Boolean)
} 