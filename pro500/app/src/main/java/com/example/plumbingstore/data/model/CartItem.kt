package com.example.plumbingstore.data.model

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "cart_items",
    primaryKeys = ["productId"],
    foreignKeys = [
        ForeignKey(
            entity = Product::class,
            parentColumns = ["id"],
            childColumns = ["productId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class CartItem(
    val productId: String,
    val quantity: Int,
    val price: Double
) 