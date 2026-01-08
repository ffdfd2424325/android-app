package com.example.plumbingstore.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shared_carts")
data class SharedCart(
    @PrimaryKey
    val id: String,
    val name: String,
    val ownerId: String,
    val totalAmount: Double,
    val participants: List<String>,
    val status: SharedCartStatus
)

enum class SharedCartStatus {
    ACTIVE,
    COMPLETED,
    CANCELLED
} 