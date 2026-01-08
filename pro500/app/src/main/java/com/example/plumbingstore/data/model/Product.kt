package com.example.plumbingstore.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Модель данных товара
 * @property id уникальный идентификатор товара
 * @property name название товара
 * @property description описание товара
 * @property price цена товара
 * @property imageUrl ссылка на изображение товара
 * @property category категория товара
 * @property brand производитель
 * @property stock количество на складе
 * @property rating рейтинг товара
 * @property isFavorite флаг избранного товара
 */
@Entity(tableName = "products")
data class Product(
    @PrimaryKey
    val id: String,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String,
    val category: String,
    val brand: String,
    val stock: Int,
    val rating: Float,
    val isFavorite: Boolean = false
) 